package com.zanchenko.alexey.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // this annotation makes this a Spring Bean
public class IndexController {
    @RequestMapping({"", "/", "index"})
    public String getIndexPage(){
        System.out.println("load index page");
        return "index"; // we are going to return back a string called index and that's going to
        // resolve to a Thymeleaf template
    }
}
