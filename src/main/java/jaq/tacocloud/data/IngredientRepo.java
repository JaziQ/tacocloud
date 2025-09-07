package jaq.tacocloud.data;

import jaq.tacocloud.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepo
        extends CrudRepository<Ingredient, String> {

}