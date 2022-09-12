insert into ingredients (id, name ) values
    (1, 'Potato'),
    (2, 'Tomato'),
    (3, 'Onion'),
    (4, 'Garlic'),
    (5, 'Capsicum'),
    (6, 'Cheese'),
    (7, 'Rice Flour'),
    (8, 'Wheat Flour'),
    (9, 'Broccoli'),
    (10, 'Green Chilly'),
    (11, 'Fish'),
    (12, 'Mutton'),
    (13, 'Pork'),
    (14, 'Salmon'),
    (15, 'Tuna'),
    (16, 'Barley'),
    (17, 'Soybean Oil'),
    (18, 'Mustard Oil'),
    (19, 'Sunflower Oil'),
    (20, 'Egg'),
    (21, 'Sugar');


insert into recipe (instructions, name, servings, vegetarian, id) values ('Fry', 'R1', 1, true, 111);
insert into recipe_ingredients (recipes_id, ingredients_id) values (111, 1);

insert into recipe (instructions, name, servings, vegetarian, id) values ('Oven', 'R2', 2, true, 112);
insert into recipe_ingredients (recipes_id, ingredients_id) values (112, 1);
insert into recipe_ingredients (recipes_id, ingredients_id) values (112, 2);

insert into recipe (instructions, name, servings, vegetarian, id) values ('Fry', 'R3', 3, true, 113);
insert into recipe_ingredients (recipes_id, ingredients_id) values (113, 1);
insert into recipe_ingredients (recipes_id, ingredients_id) values (113, 2);
insert into recipe_ingredients (recipes_id, ingredients_id) values (113, 3);

insert into recipe (instructions, name, servings, vegetarian, id) values ('Oven', 'R4', 2, true, 114);
insert into recipe_ingredients (recipes_id, ingredients_id) values (114, 3);
insert into recipe_ingredients (recipes_id, ingredients_id) values (114, 2);