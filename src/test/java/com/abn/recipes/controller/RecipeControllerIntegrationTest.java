package com.abn.recipes.controller;

import com.abn.recipes.TestHelper;
import com.abn.recipes.dto.RecipeRequest;
import com.abn.recipes.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RecipeController.class)
public class RecipeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    private static final String SAVE_RECIPE_API = "/recipe";
    private static final String GET_RECIPE_API = "/recipe/{recipeId}";
    private static final String SEARCH_RECIPE_API = "/recipe/search";

    @Test
    public void addRecipeFailureTest() throws Exception {
        RecipeRequest recipeRequest = TestHelper.getRecipeRequest();
        recipeRequest.setRecipeName(null);
        mockMvc.perform(post(SAVE_RECIPE_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(recipeRequest))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void addRecipeTest() throws Exception {
        mockMvc.perform(post(SAVE_RECIPE_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(TestHelper.getRecipeRequest()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());

        verify(recipeService, times(1)).saveRecipe(any());
    }

    @Test
    public void getRecipesTest() throws Exception {
        mockMvc.perform(get(SAVE_RECIPE_API)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(recipeService, times(1)).getRecipes(any());
    }

    @Test
    public void getRecipeTest() throws Exception {
        mockMvc.perform(get(GET_RECIPE_API,1l)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(recipeService, times(1)).getRecipe(any());
    }

    @Test
    public void updateRecipeTest() throws Exception {
        mockMvc.perform(put(GET_RECIPE_API, 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(TestHelper.getRecipeRequest()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).updateRecipe(anyLong(),any());
    }

    @Test
    public void deleteRecipeTest() throws Exception {
        mockMvc.perform(delete(GET_RECIPE_API,1l)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(recipeService, times(1)).deleteRecipe(any());
    }

    @Test
    public void searchRecipeTest() throws Exception {
        mockMvc.perform(put(SEARCH_RECIPE_API)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(TestHelper.getRecipeSearchRequest()))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(recipeService, times(1)).searchRecipes(any());
    }

}