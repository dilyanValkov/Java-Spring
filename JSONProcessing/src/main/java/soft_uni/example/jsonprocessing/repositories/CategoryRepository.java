package soft_uni.example.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import soft_uni.example.jsonprocessing.domain.entites.Category;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query(value = "select * from `product_shop`.categories ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Optional<Category> getRandomEntity();
}
