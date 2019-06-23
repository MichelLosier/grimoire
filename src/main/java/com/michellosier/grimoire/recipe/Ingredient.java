package com.michellosier.grimoire.recipe;

import com.michellosier.grimoire.model.AuditModel;

public class Ingredient extends AuditModel {
    private String name;
    private IngredientCategory category;

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
