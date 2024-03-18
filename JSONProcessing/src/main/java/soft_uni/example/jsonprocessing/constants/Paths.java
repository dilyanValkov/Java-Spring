package soft_uni.example.jsonprocessing.constants;

import java.nio.file.Path;

public enum Paths {
    ;
    public static final Path USERS_GSON_PATH =
            Path.of("src", "main", "resources","dbContent","users.json");

    public static final Path CATEGORIES_GSON_PATH =
            Path.of("src", "main", "resources","dbContent","categories.json");

    public static final Path PRODUCTS_GSON_PATH =
            Path.of("src", "main", "resources","dbContent","products.json");
}
