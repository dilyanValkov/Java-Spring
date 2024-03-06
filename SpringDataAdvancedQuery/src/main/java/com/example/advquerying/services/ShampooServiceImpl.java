package com.example.advquerying.services;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShampooServiceImpl implements ShampooService{
    private ShampooRepository shampooRepository;
@Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> getShampoosBy(Size size) {
        return this.shampooRepository.getShampoosBySize(size);
    }

    @Override
    public List<Shampoo> getShampoosBySizeOrLabel(Size size, Long labelId) {
        return this.shampooRepository.getShampoosBySizeOrLabelId(size, labelId);
    }

    @Override
    public List<Shampoo> getShampoosByPriceHigherThan(BigDecimal price) {
        return this.shampooRepository.getShampoosByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    public Long getCountOfShampoosByPrice(BigDecimal price) {
        return this.shampooRepository.countShampooByPriceIsLessThan(price);
    }

    @Override
    public List<Shampoo> getShampoosByIngredients(String ingredients) {
        return this.shampooRepository.getShampoosByIngredientsName(ingredients);
    }

    @Override
    public List<Shampoo> getShampoosByIngredientsLessThan(int number) {
        return this.shampooRepository.getShampoosByIngredientsLessThan(number);
    }
}
