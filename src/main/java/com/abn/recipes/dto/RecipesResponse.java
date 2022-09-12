package com.abn.recipes.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "Model representing the response of recipe")
@Data
public class RecipesResponse {

    @ApiModelProperty(notes = "List of recipes")
    private List<RecipeDto> recipes;
}
