package com.zanchenko.alexey.spring5recipeapp.services;

import com.zanchenko.alexey.spring5recipeapp.commands.RecipeCommand;
import com.zanchenko.alexey.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecepies();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(long anyLong);

    void deleteById(long idToDelete);
}
