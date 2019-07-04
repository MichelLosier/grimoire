package com.michellosier.grimoire;

import com.michellosier.grimoire.recipe.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class GrimoireApplication {
	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository;

	public static void main(String[] args) {
		SpringApplication.run(GrimoireApplication.class, args);
	}

	@PostConstruct
	private void testPopulateDB(){
		//Test populate
		Ingredient sugar = new Ingredient("sugar", Ingredient.IngredientCategory.SPICE);
		Ingredient water = new Ingredient("water", Ingredient.IngredientCategory.LIQUID);
		Ingredient lemon = new Ingredient("lemon", Ingredient.IngredientCategory.FRUIT);
		ingredientRepository.saveAll(Arrays.asList(sugar, water, lemon));

		RecipeIngredient lemonadeSugar = new RecipeIngredient(sugar, 1.75);
		RecipeIngredient lemonadeWater = new RecipeIngredient(water, 8.0);
		RecipeIngredient lemonadeLemon = new RecipeIngredient(lemon, 5.0);

		Set<RecipeIngredient> lemonadeIngredients = new HashSet<RecipeIngredient>();
		lemonadeIngredients.addAll(Arrays.asList(lemonadeSugar, lemonadeWater, lemonadeLemon));

		Recipe lemonade = new Recipe("Lemonade", 180000000, 18000000, "Mix it all together", lemonadeIngredients);
		recipeRepository.save(lemonade);
	}

}
