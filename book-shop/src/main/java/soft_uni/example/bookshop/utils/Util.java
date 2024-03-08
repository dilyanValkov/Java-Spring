package soft_uni.example.bookshop.utils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import soft_uni.example.bookshop.domain.entities.Author;
import soft_uni.example.bookshop.domain.entities.Book;
import soft_uni.example.bookshop.domain.model.BookDTO;
import soft_uni.example.bookshop.service.AuthorService;
import soft_uni.example.bookshop.service.BookService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Util {

    private final BookService bookService;
    private final AuthorService authorService;
    private final ModelMapper modelMapper;


    public Util (BookService bookService, AuthorService authorService, ModelMapper modelMapper){
        this.authorService = authorService;
        this.bookService = bookService;
        this.modelMapper = new ModelMapper();
    }
    public void printAllBooksByAuthor(String firstName, String lastName) {
        List<Book> books = this.bookService.getAllBooksByAuthor(firstName,lastName);
        books.forEach(book -> System.out.println(book.getBookTitleEditionTypeAndPrice()));
    }

    public void printAllAuthorsByBooksCount() {
        List<Author> authorsByBooksCount = this.authorService.getAuthorsByBooksCount();
        authorsByBooksCount.forEach(author -> System.out.println(author.getAuthorFullNameAndCountOfBooks()));
    }

    public void printAuthorsFullNameWithBooksAfter() {
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

    public void printBooksTitlesByAgeRestriction(String input){
        this.bookService.findAllByAgeRestriction(input)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void printGoldenBooks() {
        this.bookService.findAllByEditionTypeAndCopiesLessThan("Gold", 5000)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
        System.out.println();
    }

    public void printBooksByPrice() {
         this.bookService.findAllByPriceBetween(BigDecimal.valueOf(5L),BigDecimal.valueOf(40L))
                 .stream()
                 .map(Book::getBookTitleAndPriceFormat)
                 .forEach(System.out::println);
    }

    public void printNotReleasedBooks(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        this.bookService.findAllByReleaseDateIsNot(date)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void printBooksReleasedDateBefore(String input) {
        String[] dataArgs = input.split("-");
        int year = Integer.parseInt(dataArgs[2]);
        int month = Integer.parseInt(dataArgs[1]);
        int day = Integer.parseInt(dataArgs[0]);
        LocalDate date = LocalDate.of(year, month, day);
        this.bookService.findAllByReleaseDateBefore(date)
                .stream()
                .map(Book::getBookTitleAndPriceFormat)
                .forEach(System.out::println);
    }

    public void printAuthorSearch(String suffix) {
        this.authorService.getAuthorsByFirstNameEndingWith(suffix)
                .stream()
                .map(Author::getAuthorFullName)
                .forEach(System.out::println);
    }

    public void printBookSearch(String input) {
        this.bookService.findAllByTitleContaining(input)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    public void printBookTitlesSearch(String input) {
        this.bookService.findAllByAuthorLastNameEndingWith(input)
                .stream()
                .map(Book::getBookTitleEditionTypeAndPrice)
                .forEach(System.out::println);
    }

    public void printCountBooks(int number) {
        System.out.println(this.bookService.findCountByTitleLength(number));
    }

    public void printTotalBookCopies(String authorName) {


        this.bookService.findAllCopiesByAuthor(authorName)
                .stream()
                .map(Book::getCopies)
                .forEach(System.out::println);
    }

    public void printReducedBook(String input) {

        List<Book> bookList = this.bookService.findAllByTitle(input);
        TypeMap< Book,BookDTO> typeMap = modelMapper.createTypeMap(Book.class,BookDTO.class);

        typeMap.addMappings(
                modelMapper->modelMapper.map(Book::getTitle,BookDTO::setTitle));
        typeMap.addMappings(
                modelMapper->modelMapper.map(Book::getEditionType,BookDTO::setEditionType));
        typeMap.addMappings(
                modelMapper->modelMapper.map(Book::getCopies,BookDTO::setCopies));

        BookDTO bookDTO = this.modelMapper.map(bookList.get(0),BookDTO.class);
        System.out.println(bookDTO.toString());
    }
}
