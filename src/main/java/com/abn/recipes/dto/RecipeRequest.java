package com.abn.recipes.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class RecipeRequest {

    @NotBlank
    private String recipeName;

    private boolean vegetarian;

    @NotNull
    private Integer servings;

    @NotEmpty
    private Set<Long> ingredients;

    @NotBlank
    private String instructions;
}
