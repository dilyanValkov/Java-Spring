package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
        private IngredientRepository ingredientRepository;

        @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> getIngredientsWithNameStarts(String letters) {
        return this.ingredientRepository.getIngredientByNameStartingWith(letters);
    }

    @Override
    public List<Ingredient> getIngredientByName(String name) {
        return this.ingredientRepository.getIngredientsByName(name);
    }

    @Override
    public void deleteIngredientByName(String name) {
        this.ingredientRepository.deleteIngredientsByName(name);
    }

    @Override
    public void updateIngredientPrice() {
        this.ingredientRepository.updateAllPrice();
    }
}
