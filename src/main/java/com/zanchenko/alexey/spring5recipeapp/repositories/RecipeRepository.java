package com.zanchenko.alexey.spring5recipeapp.repositories;

import com.zanchenko.alexey.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.parser.Entity;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
// дергает хибер и берет данные с бд и мапит данные из бд в объекты жабы

}
