package soft_uni.example.bookshop.service;

import org.springframework.stereotype.Service;
import soft_uni.example.bookshop.domain.entities.Author;
import soft_uni.example.bookshop.domain.entities.Book;
import soft_uni.example.bookshop.domain.entities.Category;
import soft_uni.example.bookshop.domain.enums.AgeRestriction;
import soft_uni.example.bookshop.domain.enums.EditionType;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static soft_uni.example.bookshop.constants.FilePath.*;

@Service
public class SeedServiceImpl implements SeedService{
    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    public SeedServiceImpl (AuthorService authorService, BookService bookService, CategoryService categoryService){
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }
    @Override
    public void seedAuthors() throws IOException {
        if (authorService.isDataSeeded()) return;

        this.authorService.seedAuthors(
                Files.readAllLines(Path.of( FILES_DIRECTORY + AUTHOR_FILE_NAME))
                        .stream()
                        .map(firstAndLastName -> Author.builder()
                                .firstName(firstAndLastName.split(" ")[0])
                                .lastName(firstAndLastName.split(" ")[1])
                                .build())
                                .collect(Collectors.toList()));
    }

    @Override
    public void seedBooks() throws IOException {
       if (bookService.isDataSeeded()) return;

        final List<Book> books = Files.readAllLines(Path.of(FILES_DIRECTORY + BOOKS_FILE_NAME))
                        .stream()
                        .filter(b -> !b.isBlank())
                        .map(row -> {
                                    String [] arguments = row.split("\\s+");
                                    String titles = Arrays.stream(arguments)
                                            .skip(5)
                                            .collect(Collectors.joining(" "));
                                    return Book.builder()
                                            .author(this.authorService.getRandomAuthor())
                                            .categories(this.categoryService.getRandomCategories())
                                            .title(titles)
                                            .editionType(EditionType.values()[Integer.parseInt(arguments[0])])
                                            .ageRestriction(AgeRestriction.values()[Integer.parseInt(arguments[4])])
                                            .releaseDate(LocalDate.parse(arguments[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                                            .copies(Integer.parseInt(arguments[2]))
                                            .price(new BigDecimal(arguments[3]))
                                            .build();
                                }).toList();
         this.bookService.seedBooks(books);
    }

    @Override
    public void seedCategories() throws IOException {
            if (categoryService.isDataSeeded())return;
            this.categoryService.seedCategories(
                    Files.readAllLines(Path.of(FILES_DIRECTORY + CATEGORIES_FILE_NAME))
                            .stream()
                            .filter(c -> !c.isBlank())
                            .map(Category::new)
                            .collect(Collectors.toList())
            );
    }
}
