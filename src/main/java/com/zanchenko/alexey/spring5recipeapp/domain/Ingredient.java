package com.zanchenko.alexey.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;



    @ManyToOne// it is bi-directional relationship between Ingredient and Recipe
    private Recipe recipe;





    // unidirectional relationship from ingredient to UOM and we didn't do a cascade operation
    @OneToOne(fetch = FetchType.EAGER) // So you're saying hey I want this as one-to-one and by that way, Hibernate I want you to get it every time.
    private UnitOfMeasure uom; // Yeah, this is going to be retrieved every time from the database.

    public Ingredient() {}

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }
    public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }
}
