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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    @Value("${recipe.page.size}")
    private int pageSize;

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;

    /**
     * Saves into database
     * @param recipeRequest
     */
    @Override
    public void saveRecipe(RecipeRequest recipeRequest){
        log.info("Saving recipe");
        RecipeEntity entity = recipeMapper.getRecipeEntity(recipeRequest);
        recipeRepository.save(entity);
    }

    /**
     * Gets all recipes entities and map it into response object
     * @param pageNo
     * @return
     */
    @Override
    public RecipesResponse getRecipes(Integer pageNo){
        log.info("Fetching all recipes");
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<RecipeEntity> page = recipeRepository.findAll(pageable);
        log.info("Preparing response object");
        List<RecipeDto> recipeList = page.get()
                .map(recipe-> recipeMapper.getRecipeDto(recipe))
                .collect(Collectors.toList());
        RecipesResponse response = new RecipesResponse();
        response.setRecipes(recipeList);
        return response;
    }

    /**
     * Gets recipe based on its ID and map to response object
     * @param recipeId
     * @return
     */
    @Override
    public RecipesResponse getRecipe(Long recipeId){
        log.info("Fetching recipe with ID : {}", recipeId);
        Optional<RecipeEntity> optionalRecipeEntity = recipeRepository.findById(recipeId);
        RecipeEntity entity = optionalRecipeEntity.orElseThrow(() -> new RecipeServiceException("404","No record found."));
        log.info("Mapping recipe entity into recipe dto");
        RecipeDto dto = recipeMapper.getRecipeDto(entity);
        RecipesResponse response = new RecipesResponse();
        response.setRecipes(Arrays.asList(dto));
        return response;
    }

    /**
     * Updates recipe into DB by ID
     * @param recipeId
     * @param request
     */
    @Override
    public void updateRecipe(Long recipeId, RecipeRequest request){
        log.info("Updating recipe with ID : {}", recipeId);
        RecipeEntity entity = recipeMapper.getRecipeEntity(request);
        entity.setId(recipeId);
        recipeRepository.save(entity);
    }

    /**
     * Deletes recipe from DB by ID
     * @param recipeId
     */
    @Override
    public void deleteRecipe(Long recipeId){
        log.info("Delete recipe with ID : {}", recipeId);
        recipeRepository.deleteById(recipeId);
    }

    /**
     * Applies recipe specification based on the search request parameters.
     * Map the searched recipe entities into response object
     * @param request
     * @return
     */
    @Override
    public RecipesResponse searchRecipes(RecipeSearchRequest request){

        log.info("Searching for recipes with search criteria : {}", request.toString());
        Specification<RecipeEntity> and = RecipeSpecification.isVegetarian(request.getVegetarian())
                .and(RecipeSpecification.instructionsLike(request.getInstruction()))
                .and(RecipeSpecification.serves(request.getServings()))
                .and(RecipeSpecification.havingIngredients(request.getIngredientsSearch()));

        log.info("Added required recipe specifications");
        Pageable pageable = PageRequest.of(request.getPageNo()==null?0: request.getPageNo(), pageSize);
        Page<RecipeEntity> page = recipeRepository.findAll(and, pageable);
        log.info("Mapping recipe entity into recipe dto");
        List<RecipeDto> recipeList = page.get()
                .map(recipeMapper::getRecipeDto)
                .collect(Collectors.toList());
        RecipesResponse response = new RecipesResponse();
        response.setRecipes(recipeList);
        return response;
    }

}
