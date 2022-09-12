package com.abn.recipes.service.impl;

import com.abn.recipes.dto.RecipeDto;
import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.dto.RecipesResponse;
import com.abn.recipes.exception.RecipeServiceException;
import com.abn.recipes.mapper.RecipeMapper;
import com.abn.recipes.persistence.entity.RecipeEntity;
import com.abn.recipes.persistence.repository.RecipeRepository;
import com.abn.recipes.persistence.specification.RecipeSpecification;
import com.abn.recipes.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    @Value("${recipe.page.size}")
    private int pageSize;

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    @Override
    public void saveRecipe(RecipeRequest recipeRequest){
        RecipeEntity entity = recipeMapper.getRecipeEntity(recipeRequest);
        recipeRepository.save(entity);
    }

    @Override
    public RecipesResponse getRecipes(Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<RecipeEntity> page = recipeRepository.findAll(pageable);
        List<RecipeDto> recipeList = page.get()
                .map(recipe-> recipeMapper.getRecipeDto(recipe))
                .collect(Collectors.toList());
        RecipesResponse response = new RecipesResponse();
        response.setRecipes(recipeList);
        return response;
    }

    @Override
    public RecipesResponse getRecipe(Long recipeId){
        Optional<RecipeEntity> optionalRecipeEntity = recipeRepository.findById(recipeId);
        RecipeEntity entity = optionalRecipeEntity.orElseThrow(() -> new RecipeServiceException("404","No record found."));
        RecipeDto dto = recipeMapper.getRecipeDto(entity);
        RecipesResponse response = new RecipesResponse();
        response.setRecipes(Arrays.asList(dto));
        return response;
    }

    @Override
    public void updateRecipe(Long recipeId, RecipeRequest request){
        RecipeEntity entity = recipeMapper.getRecipeEntity(request);
        entity.setId(recipeId);
        recipeRepository.save(entity);
    }

    @Override
    public void deleteRecipe(Long recipeId){
        recipeRepository.deleteById(recipeId);
    }

    @Override
    public RecipesResponse searchRecipes(RecipeSearchRequest request){
        List<RecipeEntity> entities = recipeRepository.findAll(
                RecipeSpecification.havingIngredients(request.getIngredientsSearch())
                /*RecipeSpecification.isVegetarian(request.getVegetarian())
                        .and(RecipeSpecification.instructionsLike(request.getInstruction()))
                        .and(RecipeSpecification.serves(request.getServings()))
                        .and(RecipeSpecification.havingIngredients(request.getIngredientsSearch()))*/
        );

        List<RecipeDto> recipeList = entities.stream()
                .map(recipe-> recipeMapper.getRecipeDto(recipe))
                .collect(Collectors.toList());
        RecipesResponse response = new RecipesResponse();
        response.setRecipes(recipeList);
        return response;
    }

}
