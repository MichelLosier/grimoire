package com.michellosier.grimoire.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

interface RecipeRepository extends JpaRepository<Recipe, String>{

}