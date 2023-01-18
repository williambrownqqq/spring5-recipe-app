package com.zanchenko.alexey.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"}) // override.And we're doing that because when we do that, even though Data
// includes that, this allows us to set some properties on it.
// So what we want to do is use exclude and this is going to take an array and we're going
// to say, tell it to exclude recipes.
//  So this will tell it to
//exclude from EqualsAndHashcode the recipes property and we need to do this
//on the other entities as well.
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;
}

//@Entity
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String description;
//
//    @ManyToMany(mappedBy = "categories") // i said this is mapped by categories
//    private Set<Recipe> recipes; // and this is going to be on the join table from that set
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public Set<Recipe> getRecipes() {
//        return recipes;
//    }
//
//    public void setRecipes(Set<Recipe> recipes) {
//        this.recipes = recipes;
//    }
//}
