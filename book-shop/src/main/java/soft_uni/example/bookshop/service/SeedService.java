package soft_uni.example.bookshop.service;

import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;
    void seedBooks() throws IOException;
    void seedCategories() throws IOException;

    default void seedAllData() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();
    }
}
