package soft_uni.example.bookshop.service;
import soft_uni.example.bookshop.domain.entities.Author;
import soft_uni.example.bookshop.domain.entities.Book;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface BookService {
    void seedBooks(List<Book> books);
    boolean isDataSeeded ();

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> getAllBooksByAuthor(String firstName, String lastName);
    List<Book> findAllByAgeRestriction(String input);
    List<Book> findAllByEditionTypeAndCopiesLessThan(String editionType, int copies);
    List<Book> findAllByPriceBetween(BigDecimal lowerPrice, BigDecimal higherPrice);
    List<Book> findAllByReleaseDateIsNot(LocalDate year);
    List<Book> findAllByReleaseDateBefore(LocalDate year);
    List<Book> findAllByTitleContaining(String input);

    List<Book> findAllByAuthorLastNameEndingWith(String input);
    int findCountByTitleLength(int number);
    List<Book> findAllCopiesByAuthor(String author);

    List<Book> findAllByTitle(String title);
}
