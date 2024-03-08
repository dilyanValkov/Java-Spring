package soft_uni.example.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.example.bookshop.domain.entities.Author;
import soft_uni.example.bookshop.repositories.AuthorRepository;
import soft_uni.example.bookshop.repositories.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAllAndFlush(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        final long count = this.authorRepository.count();

        if (count != 0) {
            long randomId = new Random().nextLong(1L, count) + 1L;
            return this.authorRepository.findById(randomId).orElseThrow(NoSuchElementException::new);
        }

        throw new RuntimeException();
    }

    @Override
    public List<Author> getAuthorsByBooksAfter(LocalDate date) {
        return this.authorRepository.getAuthorsByBooksAfter(date);
    }

    @Override
    public List<Author> getAuthorsByBooksCount() {
        return this.authorRepository.getAuthorsByCountOfBooksDesc();
    }

    @Override
    public List<Author> getAuthorsByFirstNameEndingWith(String suffix) {
        return this.authorRepository.getAuthorsByFirstNameEndingWith(suffix);
    }
}


