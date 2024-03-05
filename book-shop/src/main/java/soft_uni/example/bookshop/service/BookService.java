package soft_uni.example.bookshop.service;
import soft_uni.example.bookshop.domain.entities.Book;
import java.time.LocalDate;
import java.util.List;


public interface BookService {
    void seedBooks(List<Book> books);
    boolean isDataSeeded ();

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> getAllBooksByAuthor(String firstName, String lastName);
}
