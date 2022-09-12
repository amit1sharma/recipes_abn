package com.abn.recipes.persistence.repository;

import com.abn.recipes.persistence.entity.IngredientsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends JpaRepository<IngredientsEntity, Long> {
}
