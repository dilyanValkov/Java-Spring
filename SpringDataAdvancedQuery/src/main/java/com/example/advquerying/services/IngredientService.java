package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import java.util.List;

public interface IngredientService {

    List<Ingredient> getIngredientsWithNameStarts(String letters);
    List<Ingredient> getIngredientByName(String name);

    void deleteIngredientByName(String name);
    void updateIngredientPrice();

}
