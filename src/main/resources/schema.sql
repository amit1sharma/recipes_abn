create sequence hibernate_sequence start with 1 increment by 1;

create table ingredients (
    id bigint not null,
    name varchar(255),
    primary key (id)
);

create table recipe (
    id bigint not null,
    instructions varchar(255),
    name varchar(255),
    servings integer,
    vegetarian boolean,
    primary key (id)
);

create table recipe_ingredients (
    recipes_id bigint not null,
    ingredients_id bigint not null,
    primary key (recipes_id, ingredients_id)
);

alter table recipe_ingredients
   add constraint recipe_ingredients_fk1
   foreign key (ingredients_id)
   references ingredients;

alter table recipe_ingredients
   add constraint recipe_ingredients_fk2
   foreign key (recipes_id)
   references recipe;