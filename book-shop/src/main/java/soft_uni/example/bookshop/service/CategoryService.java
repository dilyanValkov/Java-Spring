package soft_uni.example.bookshop.service;

import soft_uni.example.bookshop.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<Category> categories);
    boolean isDataSeeded ();

    Set<Category> getRandomCategories();
}
