package com.michellosier.grimoire.recipe;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class RecipeController {
    private final RecipeRepository recipeRepository;

    RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

}
