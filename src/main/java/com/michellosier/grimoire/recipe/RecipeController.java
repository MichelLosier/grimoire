package com.michellosier.grimoire.recipe;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private final RecipeRepository recipeRepository;

    RecipeController(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Recipe>> findAll(
            @RequestParam(value="name", required = false ) String name, //Name to do a partial string search by
            @RequestParam(value="ingredientName", required = false) String ingredientName,
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="size", defaultValue = "10") int size
    ) {

        //TODO: Make all pageable and handle case of multiple query params
        List<Recipe> recipes;
        if (name != null){
            recipes = recipeRepository.findByPartialName(name, PageRequest.of(page, size, Sort.by("name").descending()));
        } else if (ingredientName != null) {
            recipes = recipeRepository.findByIngredientName(ingredientName);
        } else {
            recipes = recipeRepository.findAll();
        }
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> findById(@PathVariable("id") Long id){
        final Recipe recipe = recipeRepository.findById(id).orElse(null);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> createNewRecipe(@RequestBody Recipe recipe){
        final Recipe newRecipe = recipeRepository.save(recipe);
        return ResponseEntity
                .created(linkTo(methodOn(RecipeController.class).findById(newRecipe.getId())).toUri())
                .body(newRecipe);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe){
        recipe.setId(id);
        final Recipe updatedRecipe = recipeRepository.save(recipe);
        return ResponseEntity
                .created(linkTo(methodOn(RecipeController.class).findById(id)).toUri())
                .body(updatedRecipe);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id){
            recipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
