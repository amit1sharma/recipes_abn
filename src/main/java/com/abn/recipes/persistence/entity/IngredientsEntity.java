package com.abn.recipes.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ingredients")
public class IngredientsEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    private Set<RecipeEntity> recipes;

}
