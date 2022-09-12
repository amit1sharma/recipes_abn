package com.abn.recipes.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@ApiModel(description = "Model representing the response dto of recipe")
@Data
public class RecipeDto {

    @ApiModelProperty(notes = "Recipe ID")
    private Long recipeId;

    @ApiModelProperty(notes = "Recipe Name")
    private String name;

    @ApiModelProperty(notes = "Recipe is vegetarian or non-vegetarian")
    private boolean vegetarian;

    @ApiModelProperty(notes = "Recipe servings")
    private Integer servings;

    @ApiModelProperty(notes = "Recipe preparations instructions")
    private String instructions;

    @ApiModelProperty(notes = "Recipe ingredients")
    private Set<String> ingredients;
}
