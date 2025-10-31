package com.masterchef.service;

import com.masterchef.model.Recipe;
import com.masterchef.model.ChefType;
import com.masterchef.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> findAll() {
        log.info("Buscando todas las recetas");
        return recipeRepository.findAllByOrderByCreatedAtDesc();
    }

    public Optional<Recipe> findByConsecutiveNumber(Long consecutiveNumber) {
        log.info("Buscando receta con número consecutivo: {}", consecutiveNumber);
        return recipeRepository.findByConsecutiveNumber(consecutiveNumber);
    }

    public List<Recipe> findByChefType(ChefType chefType) {
        log.info("Buscando recetas por tipo de chef: {}", chefType);
        return recipeRepository.findByChefType(chefType);
    }

    public List<Recipe> findBySeason(String season) {
        log.info("Buscando recetas por temporada: {}", season);
        return recipeRepository.findBySeason(season);
    }

    public List<Recipe> findByIngredient(String ingredient) {
        log.info("Buscando recetas por ingrediente: {}", ingredient);
        return recipeRepository.findByIngredientContaining(ingredient);
    }

    public Recipe save(Recipe recipe) {
        if (recipe.getConsecutiveNumber() == null) {
            recipe.setConsecutiveNumber(getNextConsecutiveNumber());
        }
        log.info("Guardando receta: {}", recipe.getTitle());
        return recipeRepository.save(recipe);
    }

    public Recipe update(Long consecutiveNumber, Recipe recipeDetails) {
        log.info("Actualizando receta con número: {}", consecutiveNumber);
        return recipeRepository.findByConsecutiveNumber(consecutiveNumber)
                .map(recipe -> {
                    recipe.setTitle(recipeDetails.getTitle());
                    recipe.setIngredients(recipeDetails.getIngredients());
                    recipe.setPreparationSteps(recipeDetails.getPreparationSteps());
                    recipe.setChefName(recipeDetails.getChefName());
                    recipe.setSeason(recipeDetails.getSeason());
                    recipe.setUpdatedAt(LocalDateTime.now());
                    return recipeRepository.save(recipe);
                })
                .orElse(null);
    }

    public void deleteByConsecutiveNumber(Long consecutiveNumber) {
        log.info("Eliminando receta con número: {}", consecutiveNumber);
        recipeRepository.deleteByConsecutiveNumber(consecutiveNumber);
    }

    public Long getNextConsecutiveNumber() {
        return recipeRepository.findTopByOrderByConsecutiveNumberDesc()
                .map(recipe -> recipe.getConsecutiveNumber() + 1)
                .orElse(1L);
    }

    public boolean existsByConsecutiveNumber(Long consecutiveNumber) {
        return recipeRepository.existsByConsecutiveNumber(consecutiveNumber);
    }
}