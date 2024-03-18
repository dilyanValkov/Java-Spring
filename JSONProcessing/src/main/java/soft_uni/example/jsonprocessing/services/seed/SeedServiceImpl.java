package soft_uni.example.jsonprocessing.services.seed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soft_uni.example.jsonprocessing.domain.entites.Category;
import soft_uni.example.jsonprocessing.domain.entites.Product;
import soft_uni.example.jsonprocessing.domain.entites.User;
import soft_uni.example.jsonprocessing.domain.models.Categoruies.CategoryImportModel;
import soft_uni.example.jsonprocessing.domain.models.Products.ProductImportModel;
import soft_uni.example.jsonprocessing.domain.models.Users.UserImportModel;
import soft_uni.example.jsonprocessing.repositories.CategoryRepository;
import soft_uni.example.jsonprocessing.repositories.ProductRepository;
import soft_uni.example.jsonprocessing.repositories.UserRepository;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static soft_uni.example.jsonprocessing.constants.Paths.*;
import static soft_uni.example.jsonprocessing.constants.Utils.GSON;
import static soft_uni.example.jsonprocessing.constants.Utils.MODEL_MAPPER;


@Service
public class SeedServiceImpl implements SeedService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }




    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(USERS_GSON_PATH.toFile());

            List<User> usersToImport = Arrays.stream(GSON.fromJson(fileReader, UserImportModel[].class))
                    .map(userImportModel -> MODEL_MAPPER.map(userImportModel, User.class))
                    .toList();

            this.userRepository.saveAllAndFlush(usersToImport);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(CATEGORIES_GSON_PATH.toFile());

            List<Category> categoryToImport = Arrays.stream(GSON.fromJson(fileReader, CategoryImportModel[].class))
                    .map(categoryImportModel -> MODEL_MAPPER.map(categoryImportModel, Category.class))
                    .toList();

            this.categoryRepository.saveAllAndFlush(categoryToImport);
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count()==0) {
            final FileReader fileReader = new FileReader(PRODUCTS_GSON_PATH.toFile());
            List<Product> productsForMapping = Arrays.stream(GSON.fromJson(fileReader, ProductImportModel[].class))
                    .map(productImportModel -> MODEL_MAPPER.map(productImportModel, Product.class))
                    .toList();

            List<Product> products = productsForMapping.stream()
                    .map(this::setRandomCategory)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomSeller)
                    .toList();
            this.productRepository.saveAllAndFlush(products);
            fileReader.close();

        }
    }
    private Product setRandomCategory(Product product){
       final Random random = new Random();
        long categoriesCount = random.nextLong(this.categoryRepository.count() - 2);
        Set<Category> categories = new HashSet<>();

        for (int i = 0; i <categoriesCount ; i++) {
            categories.add(this.categoryRepository.getRandomEntity()
                    .orElseThrow(NoSuchElementException::new));
        }
        product.setCategories(categories);
        return product;
    }

    private Product setRandomSeller(Product product){

        User seller = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

        while (product.getBuyer() != null && product.getBuyer().getId().equals(seller.getId())) {
            seller = this.userRepository
                    .getRandomEntity()
                    .orElseThrow(NoSuchElementException::new);
        }

        product.setSeller(seller);
        return product;
    }


    private Product setRandomBuyer(Product product){

       if (product.getPrice().compareTo(BigDecimal.valueOf(700L)) > 0){
           User buyer = this.userRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
           product.setBuyer(buyer);
       }
       return product;
    }
}
