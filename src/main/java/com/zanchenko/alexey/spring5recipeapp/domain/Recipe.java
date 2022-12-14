package com.zanchenko.alexey.spring5recipeapp.domain;

import org.springframework.core.io.ByteArrayResource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe") // from recipe to ingedient
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

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Notes getNotes() {
        return notes;
    }

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
