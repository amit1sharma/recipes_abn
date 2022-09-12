package com.abn.recipes.mapper;

import com.abn.recipes.dto.RecipeDto;
import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.persistence.entity.IngredientsEntity;
import com.abn.recipes.persistence.entity.RecipeEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeMapper {

    public RecipeEntity getRecipeEntity(RecipeRequest recipeRequest){
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

    public RecipeDto getRecipeDto(RecipeEntity recipe){
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
