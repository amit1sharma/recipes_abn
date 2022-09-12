package com.abn.recipes.persistence.repository;

import com.abn.recipes.persistence.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> , JpaSpecificationExecutor<RecipeEntity> {
}
