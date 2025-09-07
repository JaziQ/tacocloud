package jaq.tacocloud.web;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jaq.tacocloud.Ingredient;
import jaq.tacocloud.Ingredient.Type;


import jaq.tacocloud.Taco;
import jaq.tacocloud.data.IngredientRepo;
import jaq.tacocloud.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepo ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepo ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute Order order) {
        if(errors.hasErrors())
            return "design";
        order.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        List<Ingredient> result = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(type)) {
                result.add(ingredient);
            }
        }
        return result;
    }
}
