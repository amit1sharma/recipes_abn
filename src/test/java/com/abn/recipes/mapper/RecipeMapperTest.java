package com.abn.recipes.mapper;

import com.abn.recipes.TestHelper;
import com.abn.recipes.dto.RecipeDto;
import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.persistence.entity.RecipeEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * * @author Amit Sharma
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeMapperTest {


    @Autowired
    private RecipeMapper recipeMapper;

    @Test
    public void getRecipeEntityTest(){
        RecipeRequest recipeRequest = TestHelper.getRecipeRequest();
        RecipeEntity recipeEntity = recipeMapper.getRecipeEntity(recipeRequest);
        Assertions.assertEquals(recipeEntity.getName(), recipeRequest.getRecipeName());
    }

    @Test
    public void getRecipeDtoTest(){
        RecipeRequest recipeRequest = TestHelper.getRecipeRequest();
        RecipeEntity recipeEntity = recipeMapper.getRecipeEntity(recipeRequest);
        RecipeDto recipeDto = recipeMapper.getRecipeDto(recipeEntity);
        Assertions.assertEquals(recipeDto.getName(), recipeEntity.getName());
    }
}
