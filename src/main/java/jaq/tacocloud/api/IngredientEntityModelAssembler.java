package jaq.tacocloud.api;

import jaq.tacocloud.Ingredient;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;


public class IngredientEntityModelAssembler
        extends RepresentationModelAssemblerSupport<Ingredient, IngredientEntityModel> {

    public IngredientEntityModelAssembler() {
        super(IngredientRestController.class, IngredientEntityModel.class);
    }

    @Override
    protected IngredientEntityModel instantiateModel(Ingredient ingredient) {
        return new IngredientEntityModel(ingredient);
    }

    @Override
    public IngredientEntityModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
}