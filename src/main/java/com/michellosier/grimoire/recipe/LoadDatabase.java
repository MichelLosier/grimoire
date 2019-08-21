package com.michellosier.grimoire.recipe;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoadDatabase implements ApplicationRunner {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    //private LoadDatabase(){}; //no arg constructor
    LoadDatabase(RecipeRepository recipeRepository, IngredientRepository ingredientRepository){
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public void run(ApplicationArguments args){
        //Prepare Ingredients
        Ingredient sugar = new Ingredient("sugar", Ingredient.IngredientCategory.SPICE);
        Ingredient water = new Ingredient("water", Ingredient.IngredientCategory.LIQUID);
        Ingredient lemon = new Ingredient("lemon", Ingredient.IngredientCategory.FRUIT);
        Ingredient blackTeaBag = new Ingredient("black tea bag", Ingredient.IngredientCategory.SPICE);
        Ingredient lime = new Ingredient("lime", Ingredient.IngredientCategory.FRUIT);
        ingredientRepository.saveAll(Arrays.asList(sugar, water, lemon, lime, blackTeaBag));

        //Prepare RecipeIngredients
        RecipeIngredient sweetDrinkSugar = new RecipeIngredient(sugar, 1.75);
        RecipeIngredient sweetDrinkWater = new RecipeIngredient(water, 8.0);
        RecipeIngredient lemonadeLemon = new RecipeIngredient(lemon, 5.0);

        RecipeIngredient limeadeLime = new RecipeIngredient(lime, 5.0);

        RecipeIngredient blackTeaBagsIcedTea = new RecipeIngredient(blackTeaBag, 5.0);
        RecipeIngredient blackTeaBagsTea = new RecipeIngredient(blackTeaBag, 1.0);
        RecipeIngredient teaWater = new RecipeIngredient(water, 2.0);

        //Prepare Ingredient Sets
        Set<RecipeIngredient> lemonadeIngredients = new HashSet<>(Arrays.asList(sweetDrinkSugar, sweetDrinkWater, lemonadeLemon));
        Set<RecipeIngredient> limeadeIngredients = new HashSet<>(Arrays.asList(sweetDrinkSugar, sweetDrinkWater, limeadeLime));
        Set<RecipeIngredient> sweetTeaIngredients = new HashSet<>(Arrays.asList(sweetDrinkSugar, sweetDrinkWater, blackTeaBagsIcedTea));
        Set<RecipeIngredient> blackTeaIngredients = new HashSet<>(Arrays.asList(teaWater, blackTeaBagsTea));
        Set<RecipeIngredient> arnoldPalmerIngredients = new HashSet<>(Arrays.asList(sweetDrinkSugar, sweetDrinkWater, lemonadeLemon, blackTeaBagsIcedTea));



        Recipe lemonade = new Recipe("Lemonade", 180000000, 18000000, "Mix it all together", lemonadeIngredients);
        recipeRepository.save(lemonade);

        Recipe limeade = new Recipe("Limeade", 180000000, 18000000, "Mix it all together", limeadeIngredients);
        recipeRepository.save(limeade);

        Recipe sweetTea = new Recipe("Sweet tea", 180000000, 18000000, "Mix it all together", sweetTeaIngredients);
        recipeRepository.save(sweetTea);

        Recipe blackTea = new Recipe("Black tea", 600000, 600000, "Mix it all together", blackTeaIngredients);
        recipeRepository.save(blackTea);

        Recipe arnoldPalmer = new Recipe("Arnold Palmer", 180000000, 18000000, "Mix it all together", arnoldPalmerIngredients);
        recipeRepository.save(arnoldPalmer);


    }
}
