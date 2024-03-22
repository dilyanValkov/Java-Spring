package soft_uni.example.jsonprocessing.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class ProductsInRangeDto {
    private String firstName;
    private BigDecimal price;

    private String seller;

}
