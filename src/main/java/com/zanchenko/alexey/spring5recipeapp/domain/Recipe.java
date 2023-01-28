package com.zanchenko.alexey.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity // so this is now creting this class as Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    @Lob
    private String directions;

    //private Difficulty difficulty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true) // from recipe to ingedient // orphanRemoval - remove value from DB if it was delete from List
    private Set<Ingredient> ingredients = new HashSet<>();
    // So that defines the
    //relationship from Recipe class and we're saying that this Recipe will get stored
    //on a property on the child or the set of ingredients on each object of
    //Ingredient is going to be a property called recipe. Таким образом, это определяет отношения из класса Recipe, и мы говорим, что этот Recipe будет сохранен на свойство ребенка или набор ингредиентов на каждый объект Ингредиент будет свойством, называемым рецептом.



    @Lob // for a large object storage
    private Byte[] image;
    @Enumerated(value = EnumType.STRING ) // if we use ORIGINAL it will be persisted as 1 2 3 and that is default behavior
    private Difficulty difficulty; // STRING, it is going to get that String value of the enum and persisted as a String

    @OneToOne(cascade = CascadeType.ALL) // create the relationship for the one-to-one mapping
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category", // i wanna a table recipe_category
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id") )
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this); // build the association for the bi-directional association
        this.ingredients.add(ingredient);
        return this;
    }
}
