package soft_uni.example.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.example.jsonprocessing.domain.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
