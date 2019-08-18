package com.michellosier.grimoire.recipe;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE CONCAT('%', LOWER(:name), '%')")
    List<Recipe> findByPartialName(@Param("name") String name, Pageable pageable);

}