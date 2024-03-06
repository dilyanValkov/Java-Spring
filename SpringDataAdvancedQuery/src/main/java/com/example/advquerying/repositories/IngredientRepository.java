package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> getIngredientByNameStartingWith(String letters);
    List<Ingredient> getIngredientsByName(String name);

@Transactional
    void deleteIngredientsByName(String name);

@Modifying
@Transactional
@Query("UPDATE Ingredient SET price = price * 1.10")
void updateAllPrice();
@Modifying
@Transactional
@Query("UPDATE Ingredient SET price = price * 1.10")
void updatePriceByName();
}
