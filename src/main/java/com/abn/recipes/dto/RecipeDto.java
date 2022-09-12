package com.abn.recipes.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RecipeDto {

    private Long recipeId;

    private String name;

    private boolean vegetarian;

    private Integer servings;

    private String instructions;

    private Set<String> ingredients;
}
