package com.masterchef.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "recipes")
public class Recipe {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long consecutiveNumber;

    @NotBlank(message = "El título es obligatorio")
    private String title;

    @NotEmpty(message = "Los ingredientes son obligatorios")
    private List<String> ingredients;

    @NotEmpty(message = "Los pasos de preparación son obligatorios")
    private List<String> preparationSteps;

    @NotNull(message = "El tipo de chef es obligatorio")
    private ChefType chefType;

    @NotBlank(message = "El nombre del chef es obligatorio")
    private String chefName;

    private String season;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();
}