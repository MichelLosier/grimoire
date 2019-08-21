package com.michellosier.grimoire.recipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.michellosier.grimoire.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="recipe_ingredient")
@JsonIgnoreProperties(value={"createdAt", "updatedAt"})
public class RecipeIngredient extends AuditModel {
    @Id
    @JoinColumn(name= "recipe_id")
    @ManyToOne
    @JsonBackReference
    private Recipe recipe;

    @Id
    @JoinColumn(name = "ingredient_id")
    @ManyToOne
    private Ingredient ingredient;

    private double quantity;
    //TODO: measure unit https://www.baeldung.com/javax-measure
    public RecipeIngredient(){}// no args constructor
    public RecipeIngredient(Ingredient ingredient, Double quantity){
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
