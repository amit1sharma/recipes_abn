package com.abn.recipes.service;

import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.dto.RecipesResponse;

public interface RecipeService {
    void saveRecipe(RecipeRequest recipeRequest);

    RecipesResponse getRecipes(Integer pageNo);

    RecipesResponse getRecipe(Long recipeId);

    void updateRecipe(Long recipeId, RecipeRequest request);

    void deleteRecipe(Long recipeId);

    RecipesResponse searchRecipes(RecipeSearchRequest request);
}
