package soft_uni.example.bookshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.example.bookshop.domain.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
