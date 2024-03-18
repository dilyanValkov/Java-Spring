package soft_uni.example.jsonprocessing.domain.models.Products;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductImportModel {
    private String name;

    private BigDecimal price;
}
