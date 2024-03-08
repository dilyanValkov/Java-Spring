package soft_uni.example.bookshop;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.example.bookshop.service.AuthorService;
import soft_uni.example.bookshop.service.BookService;
import soft_uni.example.bookshop.service.SeedService;
import soft_uni.example.bookshop.utils.Util;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;
    private Scanner scanner;
    private ModelMapper modelMapper;

    @Autowired
    public ConsoleRunner (SeedService seedService, BookService bookService, AuthorService authorService){
        this.authorService = authorService;
        this.seedService = seedService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();
        Util util = new Util(bookService,authorService,modelMapper);
        //Task 1
        //util.printAllBooksAfterYear();
        //Task 2
        //util.printAuthorsFullNameWithBooksAfter();
        //Task 3
        //util.printAllAuthorsByBooksCount();
        //Task 4
        //util.printAllBooksByAuthor("Jane", "Ortiz");


        //Exercises: Spring Data Advanced Querying

        //1.Books Titles by Age Restriction
        //util.printBooksTitlesByAgeRestriction(input);

        //2.Golden Books
        //util.printGoldenBooks();

        //3.Books by Price
        //util.printBooksByPrice();

        //4.Not Released Books
        //util.printNotReleasedBooks(2000);

        //5.Books Released Before Date
        //util.printBooksReleasedDateBefore("12-04-1992");

        //6.Authors Search
        //util.printAuthorSearch(input);

        //7.Books Search
        //util.printBookSearch(input);

        //8. Book Titles Search
        //util.printBookTitlesSearch(input);

        //9. Count Books
        //util.printCountBooks(Integer.parseInt(input));

        //10. Total Book Copies
        //util.printTotalBookCopies(input);

        //11. Reduced Book
      util.printReducedBook(input);

    }


}
