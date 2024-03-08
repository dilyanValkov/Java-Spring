package soft_uni.example.bookshop.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft_uni.example.bookshop.domain.entities.Book;
import soft_uni.example.bookshop.domain.enums.AgeRestriction;
import soft_uni.example.bookshop.domain.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findAllByReleaseDateAfter(LocalDate date);
    List<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDesc(String firstName,String lastName);
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);
    List<Book> findAllByPriceBetween(BigDecimal lowerPrice, BigDecimal higherPrice);
    List<Book> findAllByReleaseDateIsNot(LocalDate year);
    List<Book> findAllByReleaseDateBefore(LocalDate year);
    List<Book> findAllByTitleContaining(String input);
    List<Book> findAllByAuthorLastNameEndingWith(String input);
    @Query("SELECT count (b) from books  as b where length(b.title) >:number")
    int findCountByTitleLength(int number);
    @Query("SELECT count (b.copies) FROM books AS b WHERE concat(' ',b.author.firstName,b.author.lastName )=:input")
    List<Book> findAllCopiesByAuthor(String input);

    List<Book> findAllByTitle(String title);






}
