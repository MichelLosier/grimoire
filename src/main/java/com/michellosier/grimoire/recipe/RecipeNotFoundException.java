package com.michellosier.grimoire.recipe;

public class RecipeNotFoundException extends RuntimeException {
    RecipeNotFoundException(Long id){
        super("Could not find recipe: " + id);
    }
}
