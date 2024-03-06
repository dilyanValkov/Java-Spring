package com.example.advquerying.repositories;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> getShampoosBySize(Size size);
    @Query(value = "SELECT s FROM Shampoo AS s" +
            " WHERE s.size = :size OR s.label.id = :id " +
            "ORDER BY s.price")
    List<Shampoo> getShampoosBySizeOrLabelId(Size size, Long id);

    List<Shampoo> getShampoosByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    Long countShampooByPriceIsLessThan(BigDecimal price);

    @Query(value = "FROM Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i.name =:name")
    List<Shampoo>getShampoosByIngredientsName(String name);
    @Query(value = "FROM Shampoo AS s " +
                   "WHERE s.ingredients.size < :number")
    List<Shampoo>getShampoosByIngredientsLessThan(int number);
}
