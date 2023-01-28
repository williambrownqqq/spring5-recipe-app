package com.zanchenko.alexey.spring5recipeapp.services;

import com.zanchenko.alexey.spring5recipeapp.commands.RecipeCommand;
import com.zanchenko.alexey.spring5recipeapp.converts.RecipeCommandToRecipe;
import com.zanchenko.alexey.spring5recipeapp.converts.RecipeToRecipeCommand;
import com.zanchenko.alexey.spring5recipeapp.domain.Recipe;
import com.zanchenko.alexey.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Slf4j // what this is going to do, is give us a logger
@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipeRepository;

    private final RecipeCommandToRecipe recipeCommandToRecipe;

    private final RecipeToRecipeCommand recipeToRecipeCommand;
    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecepies() {
        log.debug("I'm in the service");
        Set<Recipe> recipeSet = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
        return recipeSet;
    }

    @Override
    public Recipe findById(Long l) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(l);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional // we are doing a conversion outside the scope. s if we hit any lazy properties it would go kaboom
    public RecipeCommand findCommandById(long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }

    @Override
    public void deleteById(long idToDelete) {
        recipeRepository.deleteById(idToDelete);
    }


}
