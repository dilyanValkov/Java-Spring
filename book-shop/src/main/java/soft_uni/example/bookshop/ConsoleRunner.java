package soft_uni.example.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.example.bookshop.domain.entities.Author;
import soft_uni.example.bookshop.domain.entities.Book;
import soft_uni.example.bookshop.service.AuthorService;
import soft_uni.example.bookshop.service.BookService;
import soft_uni.example.bookshop.service.SeedService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private Scanner scanner;

    @Autowired
    public ConsoleRunner (SeedService seedService, BookService bookService, AuthorService authorService){
        this.authorService = authorService;
        this.seedService = seedService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        //Task 1
        //printAllBooksAfterYear();
        //Task 2
        //printAuthorsFullNameWithBooksAfter();
        //Task 3
        //printAllAuthorsByBooksCount();
        //Task 4
        printAllBooksByAuthor("Jane", "Ortiz");

    }

    private void printAllBooksByAuthor(String firstName, String lastName) {
       List<Book> books = this.bookService.getAllBooksByAuthor(firstName,lastName);
       books.forEach(book -> System.out.println(book.getBookTitleEditionTypeAndPrice()));
    }

    private void printAllAuthorsByBooksCount() {
        List<Author> authorsByBooksCount = this.authorService.getAuthorsByBooksCount();
        authorsByBooksCount.forEach(author -> System.out.println(author.getAuthorFullNameAndCountOfBooks()));
    }

    private void printAuthorsFullNameWithBooksAfter() {
        List<Author> authorsByBooksAfter = this.authorService.getAuthorsByBooksAfter(LocalDate.of(1990, 1, 1));
        for (Author author : authorsByBooksAfter) {
            System.out.println(author.getAuthorFullName());
        }

    }

    public void printAllBooksAfterYear(){
        List<Book> allByReleaseDateAfter = this.bookService.findAllByReleaseDateAfter(LocalDate.of(1999, 1, 1));
        for (Book book : allByReleaseDateAfter) {
            System.out.println(book.getBookTitleAndPriceFormat());
        }
    }
}
