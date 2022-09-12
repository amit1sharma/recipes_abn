package com.abn.recipes.service;

import com.abn.recipes.TestHelper;
import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.dto.RecipesResponse;
import com.abn.recipes.mapper.RecipeMapper;
import com.abn.recipes.persistence.entity.RecipeEntity;
import com.abn.recipes.persistence.repository.RecipeRepository;
import com.abn.recipes.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * * @author Amit Sharma
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceImplTest {

    @Autowired
    private RecipeServiceImpl recipeService;

    @MockBean
    private RecipeMapper recipeMapper;
    @MockBean
    private RecipeRepository recipeRepository;

    @Test
    public void saveRecipeTest(){
        RecipeRequest recipeRequest = TestHelper.getRecipeRequest();
        when(recipeMapper.getRecipeEntity(any())).thenReturn(TestHelper.getRecipeEntity());
        recipeService.saveRecipe(recipeRequest);
        verify(recipeRepository, times(1)).save(any());
    }

    @Test
    public void getRecipesTest(){
        when(recipeRepository.findAll(any(Pageable.class))).thenReturn(getPage());
        when(recipeMapper.getRecipeDto(any())).thenReturn(TestHelper.getRecipeDto());
        RecipesResponse recipes = recipeService.getRecipes(0);
        Assertions.assertNotNull(recipes);
    }

    @Test
    public void getRecipeTest(){
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(TestHelper.getRecipeEntity()));
        when(recipeMapper.getRecipeDto(any())).thenReturn(TestHelper.getRecipeDto());
        RecipesResponse recipes = recipeService.getRecipe(1l);
        Assertions.assertNotNull(recipes);
    }

    @Test
    public void updateRecipeTest(){

        RecipeRequest recipeRequest = TestHelper.getRecipeRequest();
        when(recipeMapper.getRecipeEntity(any())).thenReturn(TestHelper.getRecipeEntity());
        recipeService.saveRecipe(recipeRequest);
        verify(recipeRepository, times(1)).save(any());
    }

    @Test
    public void deleteRecipeTest(){
        recipeService.deleteRecipe(1l);
        verify(recipeRepository, times(1)).deleteById(any());
    }

    @Test
    public void searchRecipesTest(){
        ReflectionTestUtils.setField(recipeService, "pageSize", 5);

        when(recipeRepository.findAll(any(Specification.class),any(Pageable.class))).thenReturn(getPage());
        when(recipeMapper.getRecipeDto(any())).thenReturn(TestHelper.getRecipeDto());
        RecipesResponse recipesResponse = recipeService.searchRecipes(TestHelper.getRecipeSearchRequest());
        Assertions.assertNotNull(recipesResponse);
    }

    private Page<RecipeEntity> getPage(){
        List<RecipeEntity> recipeEntities = new ArrayList<>();
        recipeEntities.add(TestHelper.getRecipeEntity());
        Page<RecipeEntity> pagedResponse = new PageImpl(recipeEntities);
        return pagedResponse;
    }
}
