package com.zanchenko.alexey.spring5recipeapp.controllers;

import com.zanchenko.alexey.spring5recipeapp.domain.Category;
import com.zanchenko.alexey.spring5recipeapp.domain.UnitOfMeasure;
import com.zanchenko.alexey.spring5recipeapp.repositories.CategoryRepository;
import com.zanchenko.alexey.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.zanchenko.alexey.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
@Slf4j
@Controller // this annotation makes this a Spring Bean
public class IndexController {

//    private CategoryRepository categoryRepository;
//    private UnitOfMeasureRepository unitOfMeasureRepository;
//    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
//        this.categoryRepository = categoryRepository;
//        this.unitOfMeasureRepository = unitOfMeasureRepository;
//    }

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping({"", "/", "index"})
    public String getIndexPage(Model model){ // model - по сути map - отображение (model:object)
        log.debug("Getting index page");
//        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
//        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
//
//        System.out.println("Cat id is: " + categoryOptional.get().getId());
//        System.out.println("UOM id is: " + unitOfMeasureOptional.get().getId());

        model.addAttribute("recipes", recipeService.getRecepies());

        return "index"; // we are going to return back a string called index and that's going to
        // resolve to a Thymeleaf template
    }
}
