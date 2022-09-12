package com.abn.recipes.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * * @author Amit Sharma
 **/
@ApiModel(description = "Model representing the request payload for searching recipe")
@Data
public class RecipeSearchRequest {

    @ApiModelProperty(notes = "Search condition for vegetarian")
    private Boolean vegetarian;
    @ApiModelProperty(notes = "Search condition for servings")
    private Integer servings;
    @ApiModelProperty(notes = "Search condition for ingredients used")
    private IngredientsSearch ingredientsSearch;
    @ApiModelProperty(notes = "Search condition for instructions")
    private String instruction;
    @ApiModelProperty(notes = "For pagination")
    private Integer pageNo;

    @Data
    public static class IngredientsSearch{
        @ApiModelProperty(notes = "Ingredient name")
        private String text;
        @ApiModelProperty(notes = "To include or exclude from search")
        private Boolean include;
    }

}
