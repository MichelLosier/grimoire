package com.michellosier.grimoire.recipe;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Recipe findById(@PathVariable("id") Long id){
        return recipeRepository.findById(id).orElse(null);
    }

}
