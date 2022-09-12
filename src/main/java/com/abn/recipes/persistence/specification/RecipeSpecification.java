package com.abn.recipes.persistence.specification;

import com.abn.recipes.dto.RecipeSearchRequest;
import com.abn.recipes.persistence.entity.IngredientsEntity;
import com.abn.recipes.persistence.entity.RecipeEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Collection;

public class RecipeSpecification {

    public static Specification<RecipeEntity> instructionsLike(String instruction){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get(RecipeEntity.INSTRUCTIONS), "%"+instruction+"%");
    }

    public static Specification<RecipeEntity> isVegetarian(Boolean vegetarian){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(RecipeEntity.IS_VEGETARIAN), vegetarian);
    }

    public static Specification<RecipeEntity> serves(Integer servings){
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(RecipeEntity.SERVINGS), servings);
    }

    public static Specification<RecipeEntity> havingIngredients(RecipeSearchRequest.IngredientsSearch ingredientsSearch){
        return (root, query, cb) -> {
            query.distinct(true);
            Root<RecipeEntity> recipeEntityRoot = root;
            Join<RecipeEntity, IngredientsEntity> join = root.join("ingredients");
            if(ingredientsSearch.getInclude()) {
                return cb.equal(join.get("name"), ingredientsSearch.getText());
            } else{
                return cb.notEqual(join.get("name"), ingredientsSearch.getText());
            }
        };
    }

}