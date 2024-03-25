package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookImportDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import javax.validation.Validator;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final static String FILE_PATH = "src/main/resources/files/json/books.json";
    private final BookRepository bookRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, Gson gson, ModelMapper modelMapper, Validator validator, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;

        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return new String(Files.readAllBytes(Path.of(FILE_PATH)));
    }

    @Override
    public String importBooks() throws IOException {
        StringBuilder sb = new StringBuilder();

        BookImportDto[] dtos = this.gson
                .fromJson(new FileReader(FILE_PATH), BookImportDto[].class);

        for (BookImportDto dto : dtos) {
           Optional<Book> optionalBook = this.bookRepository.findBookByTitle(dto.getTitle());
            if (!validationUtil.isValid(dto)||optionalBook.isPresent()){
                sb.append("Invalid book\n");
            }else {
                Book book = this.modelMapper.map(dto, Book.class);
                this.bookRepository.saveAndFlush(book);
                sb.append(String.format("Successfully imported book %s - %s\n"
                        ,book.getAuthor(),book.getTitle()));
            }
        }
        return sb.toString();
    }
}
