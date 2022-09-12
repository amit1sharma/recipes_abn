package com.abn.recipes.controller;

import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.dto.RecipesResponse;
import com.abn.recipes.service.RecipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/recipe")
@Api(tags = {"Recipe Controller"}, value="RecipeControllerAPI",produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    /**
     * This API saves recipe
     * @param recipeRequest
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "API to save recipe")
    public void addRecipe(@RequestBody @Valid RecipeRequest recipeRequest){
        recipeService.saveRecipe(recipeRequest);
    }

    /**
     * This API fetchs all the saved recipes
     * @param pageNo
     * @return
     */
    @GetMapping
    @ApiOperation(value = "API to fetch all saved recipes")
    public RecipesResponse getRecipes(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo){
        return recipeService.getRecipes(pageNo);
    }

    /**
     * This API fetchs recipe by ID
     * @param recipeId
     * @return
     */
    @GetMapping("/{recipeId}")
    @ApiOperation(value = "API to fetch recipe by ID")
    public RecipesResponse getRecipe(@PathVariable("recipeId") Long recipeId){
        return recipeService.getRecipe(recipeId);
    }

    /**
     * This API updates recipe by ID
     * @param recipeId
     * @param recipeRequest
     */
    @PutMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "API to update recipe")
    public void updateRecipe(@PathVariable("recipeId") Long recipeId, @RequestBody @Valid RecipeRequest recipeRequest){
        recipeService.updateRecipe(recipeId, recipeRequest);
    }

    /**
     * This API deletes recipe by ID
     * @param recipeId
     */
    @DeleteMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "API to delete recipe")
    public void deleteRecipe(@PathVariable("recipeId") Long recipeId){
        recipeService.deleteRecipe(recipeId);
    }

    /**
     * This API allows to search for recipes based on several search criteria.
     * All the request parameters are optional. If provided will apply search on that key
     * @param searchRequest
     * @return
     */
    @PutMapping("/search")
    @ApiOperation(value = "API to search recipe either by instructions/servings/is vegetarian/ingredients ")
    public RecipesResponse search(@RequestBody RecipeSearchRequest searchRequest){
        return recipeService.searchRecipes(searchRequest);
    }
}
