package com.zanchenko.alexey.spring5recipeapp.services;

import com.zanchenko.alexey.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.zanchenko.alexey.spring5recipeapp.domain.UnitOfMeasure;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> listAllUoms();
}
