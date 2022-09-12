package com.abn.recipes;

import com.abn.recipes.dto.RecipeDto;
import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.persistence.entity.IngredientsEntity;
import com.abn.recipes.persistence.entity.RecipeEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * * @author Amit Sharma
 **/
public class TestHelper {

    public static RecipeRequest getRecipeRequest(){
        RecipeRequest request = new RecipeRequest();
        request.setRecipeName("Test");
        request.setInstructions("Test");
        request.setServings(3);
        request.setVegetarian(true);
        Set<Long> ingredients = new HashSet<>();
        ingredients.add(1l);
        request.setIngredients(ingredients);
        return request;
    }

    public static RecipeSearchRequest getRecipeSearchRequest(){
        RecipeSearchRequest request = new RecipeSearchRequest();
        request.setServings(2);
        request.setVegetarian(true);
        request.setInstruction("test");
        request.setPageNo(0);
        request.setIngredientsSearch(new RecipeSearchRequest.IngredientsSearch());
        return request;
    }

    public static RecipeEntity getRecipeEntity(){

        RecipeRequest recipeRequest =  getRecipeRequest();
        Set<IngredientsEntity> ingredientsEntities = recipeRequest.getIngredients()
                .stream()
                .map(aLong -> {
                    IngredientsEntity ingredientsEntity = new IngredientsEntity();
                    ingredientsEntity.setId(aLong);
                    return ingredientsEntity;
                })
                .collect(Collectors.toSet());
        RecipeEntity entity = new RecipeEntity();
        entity.setInstructions(recipeRequest.getInstructions());
        entity.setIngredients(ingredientsEntities);
        entity.setName(recipeRequest.getRecipeName());
        entity.setServings(recipeRequest.getServings());
        entity.setVegetarian(recipeRequest.isVegetarian());
        return entity;
    }

    public static RecipeDto getRecipeDto(){
        RecipeEntity recipe = getRecipeEntity();
        RecipeDto dto = new RecipeDto();
        dto.setName(recipe.getName());
        dto.setInstructions(recipe.getInstructions());
        dto.setServings(recipe.getServings());
        dto.setVegetarian(recipe.isVegetarian());
        dto.setRecipeId(recipe.getId());
        Set<String> ingredients = recipe.getIngredients()
                .stream()
                .map(IngredientsEntity::getName)
                .collect(Collectors.toSet());

        dto.setIngredients(ingredients);
        return dto;
    }
}
