package com.michellosier.grimoire.recipe;

import com.michellosier.grimoire.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="RECIPE")
public class Recipe extends AuditModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @NotNull
    private String name;
    private Integer prepTime;
    private Integer cookTime;
    private String instructions;

    @ManyToMany(targetEntity=Ingredient.class)
    @Size(min=1, message="Recipe requires at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    public Recipe(@NotNull String name, Integer prepTime, Integer cookTime, String instructions, @Size(min = 1, message = "Recipe requires at least 1 ingredient") List<Ingredient> ingredients) {
        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
