package com.masterchef.controller;

import com.masterchef.model.Recipe;
import com.masterchef.model.ChefType;
import com.masterchef.service.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
@Tag(name = "Recetas", description = "API de gestión de recetas de Master Chef Celebrity")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/viewer")
    @Operation(summary = "Registrar receta de televidente")
    public ResponseEntity<Recipe> createViewerRecipe(@Valid @RequestBody Recipe recipe) {
        log.info("Creando receta de televidente: {}", recipe.getTitle());
        recipe.setChefType(ChefType.VIEWER);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.save(recipe));
    }

    @PostMapping("/participant")
    @Operation(summary = "Registrar receta de participante")
    public ResponseEntity<?> createParticipantRecipe(@Valid @RequestBody Recipe recipe) {
        if (recipe.getSeason() == null || recipe.getSeason().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("La temporada es obligatoria para participantes");
        }
        log.info("Creando receta de participante: {}", recipe.getTitle());
        recipe.setChefType(ChefType.PARTICIPANT);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.save(recipe));
    }

    @PostMapping("/chef")
    @Operation(summary = "Registrar receta de chef")
    public ResponseEntity<Recipe> createChefRecipe(@Valid @RequestBody Recipe recipe) {
        log.info("Creando receta de chef: {}", recipe.getTitle());
        recipe.setChefType(ChefType.CHEF);
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.save(recipe));
    }

    @GetMapping
    @Operation(summary = "Obtener todas las recetas")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        log.info("Obteniendo todas las recetas");
        return ResponseEntity.ok(recipeService.findAll());
    }

    @GetMapping("/{consecutiveNumber}")
    @Operation(summary = "Obtener receta por número consecutivo")
    public ResponseEntity<Recipe> getRecipeByConsecutiveNumber(@PathVariable Long consecutiveNumber) {
        log.info("Buscando receta con número: {}", consecutiveNumber);
        return recipeService.findByConsecutiveNumber(consecutiveNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/participant")
    @Operation(summary = "Obtener recetas de participantes")
    public ResponseEntity<List<Recipe>> getParticipantRecipes() {
        return ResponseEntity.ok(recipeService.findByChefType(ChefType.PARTICIPANT));
    }

    @GetMapping("/type/viewer")
    @Operation(summary = "Obtener recetas de televidentes")
    public ResponseEntity<List<Recipe>> getViewerRecipes() {
        return ResponseEntity.ok(recipeService.findByChefType(ChefType.VIEWER));
    }

    @GetMapping("/type/chef")
    @Operation(summary = "Obtener recetas de chefs")
    public ResponseEntity<List<Recipe>> getChefRecipes() {
        return ResponseEntity.ok(recipeService.findByChefType(ChefType.CHEF));
    }

    @GetMapping("/season/{season}")
    @Operation(summary = "Obtener recetas por temporada")
    public ResponseEntity<List<Recipe>> getRecipesBySeason(@PathVariable String season) {
        return ResponseEntity.ok(recipeService.findBySeason(season));
    }

    @GetMapping("/search/ingredient")
    @Operation(summary = "Buscar recetas por ingrediente")
    public ResponseEntity<List<Recipe>> searchRecipesByIngredient(@RequestParam String ingredient) {
        if (ingredient == null || ingredient.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(recipeService.findByIngredient(ingredient));
    }

    @DeleteMapping("/{consecutiveNumber}")
    @Operation(summary = "Eliminar receta")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long consecutiveNumber) {
        if (!recipeService.existsByConsecutiveNumber(consecutiveNumber)) {
            return ResponseEntity.notFound().build();
        }
        recipeService.deleteByConsecutiveNumber(consecutiveNumber);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{consecutiveNumber}")
    @Operation(summary = "Actualizar receta")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long consecutiveNumber,
                                               @Valid @RequestBody Recipe recipeDetails) {
        Recipe updatedRecipe = recipeService.update(consecutiveNumber, recipeDetails);
        return updatedRecipe != null ?
                ResponseEntity.ok(updatedRecipe) :
                ResponseEntity.notFound().build();
    }
}