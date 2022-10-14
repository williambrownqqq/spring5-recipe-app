package com.zanchenko.alexey.spring5recipeapp.domain;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne()
    private Recipe recipe; // we don't need to specify a
    //cascade because this instance we are going to allow the Recipe to own this.
    //We don't want if we delete the Notes object, we don't want to go back and delete the Recipe object.
    //So we don't want cascade, operations happening but the inverse, if we want to delete a Recipe, of course
    //we want to delete the recipeNotes.
    @Lob
    private String recipeNotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }
}
