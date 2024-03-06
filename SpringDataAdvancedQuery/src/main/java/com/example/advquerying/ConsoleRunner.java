package com.example.advquerying;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.services.IngredientService;
import com.example.advquerying.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;
    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService =  ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {

        //1. Select Shampoos by Size
        //printShampoosBySize("medium");

        //2. Select Shampoos by Size or Label
        //printShampoosBySizeOrLabel("Medium",10L);

        //3. Select Shampoos by Price
        //printShampoosWithPriceHigherThan(new BigDecimal(5));

        //4. Select Ingredients by Name
        //printIngredientsStartsWithName("m");

        //5. Select Ingredients by Names
        //List<String> ingrNames = List.of("Berry", "Mineral-Collage");
        //printIngredientsByNames(ingrNames);

        //6. Count Shampoos by Price
        //printCountOfShampoosByPrice(new BigDecimal("8.50"));

        //7. Select Shampoos by Ingredients
        //printShampoosByIngredients(ingrNames);

        //8. Select Shampoos by Ingredients Count
        //printShampoosByIngredientsLessThan(2);

        //9. Delete Ingredients by Name
        //this.ingredientService.deleteIngredientByName("Apple");

        //10. Update Ingredients by Price
            this.ingredientService.updateIngredientPrice();
    }

    private void printShampoosByIngredientsLessThan(int number) {
        List<Shampoo> shampoosByIngredientsLessThan = this.shampooService.getShampoosByIngredientsLessThan(number);
        for (Shampoo shampoo : shampoosByIngredientsLessThan) {
            System.out.println(shampoo.getBrand());
        }
    }

    private void printShampoosByIngredients(List<String> names) {
        for (String name : names) {
            List<Shampoo> shampoosByIngredients = this.shampooService.getShampoosByIngredients(name);
            for (Shampoo shampoosByIngredient : shampoosByIngredients) {
                String brand = shampoosByIngredient.getBrand();
                System.out.println(brand);
            }
        }

    }

    private void printCountOfShampoosByPrice(BigDecimal price) {
        System.out.println(this.shampooService.getCountOfShampoosByPrice(price));
    }


    private void printIngredientsByNames(List<String> names) {
            for (int i = 0; i < names.size() ; i++) {
                String name = names.get(i);
            List<Ingredient> ingredientByName = this.ingredientService.getIngredientByName(name);
            System.out.println(ingredientByName);
        }
    }

    private void printIngredientsStartsWithName(String letters) {
        List<Ingredient> ingredients = this.ingredientService.getIngredientsWithNameStarts(letters);
        ingredients.forEach(ingredient ->
                System.out.println(ingredient.stringFormatIngredientsName()));
    }

    private void printShampoosWithPriceHigherThan(BigDecimal price) {
        List<Shampoo> shamooList = shampooService.getShampoosByPriceHigherThan(price);
        shamooList.forEach(shampoo -> System.out.println(shampoo.stringFormatShampooBrandSizePrice()));
    }

    private void printShampoosBySizeOrLabel(String size, Long labelId) {
        Size parsedSize = Size.valueOf(size.toUpperCase());
        List<Shampoo> shamooList = shampooService.getShampoosBySizeOrLabel(parsedSize,labelId);
        shamooList.forEach(shampoo -> System.out.println(shampoo.stringFormatShampooBrandSizePrice()));
    }

    private void printShampoosBySize(String size) {
        Size parsedSize = Size.valueOf(size.toUpperCase());
        List<Shampoo> shamooList = shampooService.getShampoosBy(parsedSize);
        shamooList.forEach(shampoo -> System.out.println(shampoo.stringFormatShampooBrandSizePrice()));
    }
}
