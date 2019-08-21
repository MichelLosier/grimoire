package com.michellosier.grimoire.recipe;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

//Deprecated use of HATEOS
//@Component
public class RecipeResourceAssembler implements ResourceAssembler<Recipe, Resource<Recipe>> {

    @Override
    public Resource<Recipe> toResource(Recipe recipe) {
        Resource<Recipe> recipeResource = new Resource<>(recipe,
                linkTo(methodOn(RecipeController.class).findById(recipe.getId())).withSelfRel(),
                linkTo(methodOn(RecipeController.class).findAll(null, null, 0, 5)).withRel("recipes")
        );
        return recipeResource;
    }
}
