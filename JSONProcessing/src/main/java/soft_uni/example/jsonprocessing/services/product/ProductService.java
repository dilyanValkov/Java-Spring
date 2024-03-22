package soft_uni.example.jsonprocessing.services.product;

import soft_uni.example.jsonprocessing.domain.entites.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void seedProducts();
    List<Product> getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high);
}
