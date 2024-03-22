package soft_uni.example.jsonprocessing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soft_uni.example.jsonprocessing.domain.entites.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<List<Product>> getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high);
}
