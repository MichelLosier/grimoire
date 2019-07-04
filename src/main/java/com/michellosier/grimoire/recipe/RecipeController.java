package com.michellosier.grimoire.recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

}
