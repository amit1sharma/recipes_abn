package com.abn.recipes.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "recipe")
public class RecipeEntity {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IS_VEGETARIAN = "vegetarian";
    public static final String SERVINGS = "servings";
    public static final String INSTRUCTIONS = "instructions";

    @Id
    @Column(name = ID)
    @GeneratedValue
    private long id;

    @Column(name = NAME)
    private String name;

    @Column(name = IS_VEGETARIAN)
    private boolean vegetarian;

    @Column(name = SERVINGS)
    private Integer servings;

    @Column(name = INSTRUCTIONS)
    private String instructions;

    @ManyToMany
    private Set<IngredientsEntity> ingredients;
}
