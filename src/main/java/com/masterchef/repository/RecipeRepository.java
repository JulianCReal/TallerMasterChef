package com.masterchef.repository;

import com.masterchef.model.Recipe;
import com.masterchef.model.ChefType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {

    Optional<Recipe> findByConsecutiveNumber(Long consecutiveNumber);
    List<Recipe> findByChefType(ChefType chefType);
    List<Recipe> findBySeason(String season);

    @Query("{ 'ingredients': { $regex: ?0, $options: 'i' } }")
    List<Recipe> findByIngredientContaining(String ingredient);

    List<Recipe> findAllByOrderByCreatedAtDesc();
    Optional<Recipe> findTopByOrderByConsecutiveNumberDesc();
    boolean existsByConsecutiveNumber(Long consecutiveNumber);
    void deleteByConsecutiveNumber(Long consecutiveNumber);
}