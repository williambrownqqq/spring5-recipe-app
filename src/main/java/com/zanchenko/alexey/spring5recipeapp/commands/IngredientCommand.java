package com.zanchenko.alexey.spring5recipeapp.commands;

import com.zanchenko.alexey.spring5recipeapp.domain.Recipe;
import com.zanchenko.alexey.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {

    private Long id;

    private Long recipeId;

    private String description;

    private BigDecimal amount;

    private UnitOfMeasureCommand uom;

    public void setUnitOfMeasure(UnitOfMeasureCommand unitOfMeasureCommand) {
        this.uom = unitOfMeasureCommand;
    }

    public UnitOfMeasureCommand getUnitOfMeasure() {
        return uom;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }
}
