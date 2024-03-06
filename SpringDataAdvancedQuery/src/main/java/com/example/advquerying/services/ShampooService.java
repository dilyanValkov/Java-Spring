package com.example.advquerying.services;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {
    List<Shampoo> getShampoosBy(Size size);

    List<Shampoo> getShampoosBySizeOrLabel(Size size, Long labelId);

    List<Shampoo> getShampoosByPriceHigherThan(BigDecimal price);

    Long getCountOfShampoosByPrice(BigDecimal price);
    List<Shampoo> getShampoosByIngredients(String ingredients);
    List<Shampoo> getShampoosByIngredientsLessThan(int number);

}
