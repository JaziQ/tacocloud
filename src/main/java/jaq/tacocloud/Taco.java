package jaq.tacocloud;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
public class Taco {
    @NotNull
    @Size(min=4, message="Name must be at least 4 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;
    
}
