package soft_uni.example.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.example.bookshop.domain.entities.Book;
import soft_uni.example.bookshop.repositories.BookRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public void seedBooks(List<Book> books) {
        this.bookRepository.saveAllAndFlush(books);
    }

    @Override
    public boolean isDataSeeded() {
        return this.bookRepository.count() > 0;
    }

    @Override
    public List <Book> findAllByReleaseDateAfter(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateAfter(date).get();
    }

    @Override
    public List<Book> getAllBooksByAuthor(String firstName, String lastName) {
         return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDesc(firstName,lastName);
    }



}
