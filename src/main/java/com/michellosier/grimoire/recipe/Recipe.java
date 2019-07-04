package com.michellosier.grimoire.recipe;

import com.michellosier.grimoire.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="recipe")
public class Recipe extends AuditModel {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final String id;

    @NotNull
    private String name;
    private Integer prepTime;
    private Integer cookTime;
    private String instructions;

    @OneToMany(mappedBy = "recipe")
    @Size(min=1, message="Recipe requires at least 1 ingredient")
    private Set<RecipeIngredient> recipeIngredients;

    private Recipe(){ this.id = null; }; //no arg constructor

    public Recipe(@NotNull String name, Integer prepTime, Integer cookTime, String instructions,
                  @Size(min = 1, message = "Recipe requires at least 1 ingredient") Set<RecipeIngredient> recipeIngredients) {
        this.name = name;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.instructions = instructions;

        for (RecipeIngredient recipeIngredient : recipeIngredients){
            recipeIngredient.setRecipe(this);
        }
        this.recipeIngredients = recipeIngredients;
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

    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }
}
