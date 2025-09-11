package jaq.tacocloud;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    @Id
    private ObjectId id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=4, message="Name must be at least 4 characters long")
    private String name;

    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
