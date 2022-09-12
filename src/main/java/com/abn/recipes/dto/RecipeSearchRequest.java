package com.abn.recipes.dto;

import lombok.Data;

/**
 * * @author Amit Sharma
 **/
@Data
public class RecipeSearchRequest {

    private Boolean vegetarian;
    private Integer servings;
    private IngredientsSearch ingredientsSearch;
    private String instruction;

    @Data
    public static class IngredientsSearch{
        private String text;
        private Boolean include;
    }

}
