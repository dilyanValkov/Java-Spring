package soft_uni.example.jsonprocessing.services.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.example.jsonprocessing.domain.entites.Product;
import soft_uni.example.jsonprocessing.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
@Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void seedProducts() {

    }

    @Override
    public List<Product> getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) {
        return this.productRepository.getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .orElseThrow(NoSuchElementException::new);
    }
}
