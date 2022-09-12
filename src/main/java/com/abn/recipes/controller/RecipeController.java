package com.abn.recipes.controller;

import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.dto.RecipesResponse;
import com.abn.recipes.service.RecipeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Api(value="RecipeControllerAPI",produces = MediaType.APPLICATION_JSON_VALUE)
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addRecipe(@RequestBody @Valid RecipeRequest recipeRequest){
        recipeService.saveRecipe(recipeRequest);
    }

    @GetMapping
    public RecipesResponse getRecipes(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo){
        return recipeService.getRecipes(pageNo);
    }

    @GetMapping("/{recipeId}")
    public RecipesResponse getRecipe(@PathVariable("recipeId") Long recipeId){
        return recipeService.getRecipe(recipeId);
    }

    @PutMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public void getRecipe(@PathVariable("recipeId") Long recipeId, @RequestBody @Valid RecipeRequest recipeRequest){
        recipeService.updateRecipe(recipeId, recipeRequest);
    }

    @DeleteMapping("/{recipeId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecipe(@PathVariable("recipeId") Long recipeId){
        recipeService.deleteRecipe(recipeId);
    }

    @PutMapping("/search")
    public RecipesResponse search(@RequestBody @Valid RecipeSearchRequest searchRequest){
        return recipeService.searchRecipes(searchRequest);
    }
}
