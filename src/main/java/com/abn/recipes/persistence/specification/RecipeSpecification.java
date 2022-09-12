package com.abn.recipes.persistence.specification;

import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.persistence.entity.IngredientsEntity;
import com.abn.recipes.persistence.entity.RecipeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 * This class is used to return different recipe specifications that are used for filtering out
 * based on request parameters.
 */
public class RecipeSpecification {


    /**
     * To fetch all records
     * @return
     */
    public static Specification<RecipeEntity> all(){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(RecipeEntity.ID), root.get(RecipeEntity.ID));
    }

    /**
     * Adds filter condition on recipe instructions
     * @param instruction
     * @return
     */
    public static Specification<RecipeEntity> instructionsLike(String instruction){
        if(!StringUtils.hasText(instruction))
            return all();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(RecipeEntity.INSTRUCTIONS), "%"+instruction+"%");
    }

    /**
     * Adds filter condition on isVegetarian
     * @param vegetarian
     * @return
     */
    public static Specification<RecipeEntity> isVegetarian(Boolean vegetarian){
        if(vegetarian == null)
            return all();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(RecipeEntity.IS_VEGETARIAN), vegetarian);
    }

    /**
     * Adds filter condition on number of servings
     * @param servings
     * @return
     */
    public static Specification<RecipeEntity> serves(Integer servings){
        if(servings == null || servings<=0)
            return all();
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(RecipeEntity.SERVINGS), servings);
    }

    /**
     * Adds filter condition on the basis of ingredients used into recipe.
     * @param ingredientsSearch
     * @return
     */
    public static Specification<RecipeEntity> havingIngredients(RecipeSearchRequest.IngredientsSearch ingredientsSearch){
        if(ingredientsSearch == null)
            return all();
        return (root, query, cb) -> {
            query.distinct(true);
            Root<RecipeEntity> recipeEntityRoot = root;
            Join<RecipeEntity, IngredientsEntity> join = root.join("ingredients");
            if(ingredientsSearch.getInclude()) {
                return cb.equal(cb.upper(join.get("name")), ingredientsSearch.getText().toUpperCase());
            } else{
                return cb.notEqual(cb.upper(join.get("name")), ingredientsSearch.getText().toUpperCase());
            }
        };
    }

}