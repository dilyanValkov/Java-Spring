package soft_uni.example.bookshop.service;

import soft_uni.example.bookshop.domain.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    void seedAuthors (List <Author> authors);
    boolean isDataSeeded ();

    Author getRandomAuthor();
    List<Author> getAuthorsByBooksAfter (LocalDate date);
    List<Author> getAuthorsByBooksCount();

}
