package com.michellosier.grimoire.recipe;

import com.michellosier.grimoire.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="RecipeIngredient")
public class RecipeIngredient extends AuditModel {
    @Id
    @JoinColumn(name= "recipe_id")
    @ManyToOne
    private Recipe recipe;

    @Id
    @JoinColumn(name = "ingredient_id")
    @ManyToOne
    private Ingredient ingredient;

    private double quantity;
    //TODO: measure unit https://www.baeldung.com/javax-measure

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Double quantity){

    }
}
