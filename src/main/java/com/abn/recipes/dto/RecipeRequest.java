package com.abn.recipes.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@ApiModel(description = "Model representing the request payload for adding recipe.")
@Data
public class RecipeRequest {

    @ApiModelProperty(notes = "Recipe name")
    @NotBlank
    private String recipeName;

    @ApiModelProperty(notes = "If recipe is vegetarian or non-vegetarian")
    private boolean vegetarian;

    @ApiModelProperty(notes = "Total number of serving this recipe has")
    @NotNull
    private Integer servings;

    @ApiModelProperty(notes = "IDs of the ingredients that are required to prepare this recipe")
    @NotEmpty
    private Set<Long> ingredients;

    @ApiModelProperty(notes = "Instructions to follow to prepare this recipe")
    @NotBlank
    private String instructions;
}
