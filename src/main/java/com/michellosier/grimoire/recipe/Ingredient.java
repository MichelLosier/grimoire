package com.michellosier.grimoire.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.michellosier.grimoire.model.AuditModel;

import javax.persistence.*;

@Entity
@Table(name="ingredient")
@JsonIgnoreProperties(value={"createdAt", "updatedAt"})
public class Ingredient extends AuditModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String name;
    private IngredientCategory category;

//    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL)
//    private Set<RecipeIngredient> recipes;

    public static enum IngredientCategory {
        FRUIT,
        VEGETABLE,
        MEAT,
        BEAN,
        DAIRY,
        NUT,
        GRAIN,
        LIQUID,
        SPICE,
        OIL,
        OTHER
    }

    public Ingredient(){}// no args constructor

    public Ingredient(String name, IngredientCategory category) {
        this.name = name;
        this.category = category;
    }

    public Long getId(){
        return id;
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
