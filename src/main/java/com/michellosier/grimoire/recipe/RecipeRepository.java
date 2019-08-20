package com.michellosier.grimoire.recipe;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Recipe> findByPartialName(@Param("name") String name, Pageable pageable);

    @Query("SELECT r FROM Recipe r JOIN RecipeIngredient ri ON r.id = ri.recipe JOIN Ingredient i ON i.id =  ri.ingredient  WHERE LOWER(i.name) = LOWER(:ingredientName)")
    List<Recipe> findByIngredientName(@Param("ingredientName") String ingredientName);

}