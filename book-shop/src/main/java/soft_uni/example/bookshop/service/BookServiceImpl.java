package soft_uni.example.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.example.bookshop.domain.entities.Author;
import soft_uni.example.bookshop.domain.entities.Book;
import soft_uni.example.bookshop.domain.enums.AgeRestriction;
import soft_uni.example.bookshop.domain.enums.EditionType;
import soft_uni.example.bookshop.repositories.BookRepository;

import java.math.BigDecimal;
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

    @Override
    public List<Book> findAllByAgeRestriction(String input) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(input.toUpperCase());
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopiesLessThan(String input, int copies) {
        EditionType editionType = EditionType.valueOf(input.toUpperCase());
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType,copies);
    }

    @Override
    public List<Book> findAllByPriceBetween(BigDecimal lowerPrice, BigDecimal higherPrice) {
        return this.bookRepository.findAllByPriceBetween(lowerPrice,higherPrice);
    }

    @Override
    public List<Book> findAllByReleaseDateIsNot(LocalDate year) {
        return this.bookRepository.findAllByReleaseDateIsNot(year);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate year) {
        return this.bookRepository.findAllByReleaseDateBefore(year);
    }

    @Override
    public List<Book> findAllByTitleContaining(String input) {
        return this.bookRepository.findAllByTitleContaining(input);
    }

    @Override
    public List<Book> findAllByAuthorLastNameEndingWith(String input) {
        return this.bookRepository.findAllByAuthorLastNameEndingWith(input);
    }

    @Override
    public int findCountByTitleLength(int number) {
        return this.bookRepository.findCountByTitleLength(number);
    }

    @Override
    public List<Book> findAllCopiesByAuthor(String author) {
        return this.bookRepository.findAllCopiesByAuthor(author);
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        return this.bookRepository.findAllByTitle(title);
    }


}
