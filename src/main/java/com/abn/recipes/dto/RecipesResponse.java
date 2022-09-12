package com.abn.recipes.dto;

import lombok.Data;

import java.util.List;

@Data
public class RecipesResponse {
    private List<RecipeDto> recipes;
}
