package jaq.tacocloud.web;

import jaq.tacocloud.data.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import jaq.tacocloud.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepo ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepo ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientRepo.findById(id).orElse(null);
    }
}
