package soft_uni.example.jsonprocessing;

import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import soft_uni.example.jsonprocessing.constants.Utils;
import soft_uni.example.jsonprocessing.domain.entites.Product;
import soft_uni.example.jsonprocessing.domain.models.Products.ProductImportModel;
import soft_uni.example.jsonprocessing.services.product.ProductService;
import soft_uni.example.jsonprocessing.services.seed.SeedService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandRunner implements CommandLineRunner {
    private final SeedService service;
    private final ProductService productService;


@Autowired
    public CommandRunner(SeedService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
}

    @Override
    public void run(String... args) throws Exception {
        //this.service.seedAll();


        List<Product> productList = this.productService.getProductsByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500L), BigDecimal.valueOf(1000));
        ProductImportModel map = Utils.MODEL_MAPPER.map(productList, ProductImportModel.class);
        List<ProductImportModel> productImport = new ArrayList<>();

        List<ProductImportModel> collect = productList.stream().map(product ->
                Utils.MODEL_MAPPER.map(productList, ProductImportModel.class)).toList();
        System.out.println();

    }
}
