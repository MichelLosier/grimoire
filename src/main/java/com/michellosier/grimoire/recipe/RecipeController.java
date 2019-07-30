package com.michellosier.grimoire.recipe;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/recipe")
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeResourceAssembler assembler;

    RecipeController(RecipeRepository recipeRepository,
                     RecipeResourceAssembler assembler
                     ){
        this.recipeRepository = recipeRepository;
        this.assembler = assembler;
    }

    @GetMapping(produces = "application/json; charset=UTF-8")
    public Resources<Resource<Recipe>> findAll() {
        List<Resource<Recipe>> recipes = recipeRepository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources<>(recipes, linkTo(methodOn(RecipeController.class).findAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Recipe> findById(@PathVariable("id") Long id){
        return assembler.toResource(recipeRepository.findById(id).orElse(null));
    }

    @PostMapping(produces = "application/json; charset=UTF-8")
    public ResponseEntity<Resource<Recipe>> createNewRecipe(@RequestBody Recipe recipe){
        Recipe newRecipe = recipeRepository.save(recipe);
        return ResponseEntity
                .created(linkTo(methodOn(RecipeController.class).findById(newRecipe.getId())).toUri())
                .body(assembler.toResource(newRecipe));
    }

    @PutMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Resource<Recipe>> updateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe){
        recipe.setId(id);
        return ResponseEntity
                .created(linkTo(methodOn(RecipeController.class).findById(id)).toUri())
                .body(assembler.toResource(recipeRepository.save(recipe)));

    }

//    @PatchMapping("/{id}")
//    public Recipe partialUpdateRecipe(@PathVariable("id") Long id, @RequestBody Recipe recipe){
//        return recipeRepository.save(recipe);
//    }

    @DeleteMapping(value = "/{id}", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id){
            recipeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }

}
