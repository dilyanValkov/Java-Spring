package soft_uni.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft_uni.example.bookshop.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {

    @Query(value = "SELECT a FROM Author AS a " +
            "JOIN books as b WHERE b.releaseDate < :localDate")
     List<Author> getAuthorsByBooksAfter(LocalDate localDate);
@Query(value = "SELECT a FROM Author as a " +
        "ORDER BY size(a.books) DESC "
)
    List<Author> getAuthorsByCountOfBooksDesc();
}
