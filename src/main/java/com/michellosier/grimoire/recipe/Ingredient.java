package com.michellosier.grimoire.recipe;

import com.michellosier.grimoire.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="Ingredient")
public class Ingredient extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private final String id;

    private String name;
    private IngredientCategory category;

    public static enum IngredientCategory {
        FRUIT,
        VEGETABLE,
        MEAT,
        BEAN,
        DAIRY,
        NUT,
        GRAIN,
    }

    private Ingredient(){this.id = null}// no args constructor

    public Ingredient(String name, IngredientCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }
}
