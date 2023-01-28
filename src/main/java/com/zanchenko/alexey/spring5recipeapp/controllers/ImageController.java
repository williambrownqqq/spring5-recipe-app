package com.zanchenko.alexey.spring5recipeapp.controllers;

import com.zanchenko.alexey.spring5recipeapp.commands.RecipeCommand;
import com.zanchenko.alexey.spring5recipeapp.services.ImageService;
import com.zanchenko.alexey.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;
    private final RecipeService recipeService;

    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }
    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){ // this is going to return the form
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        log.debug("we came to the image form");
        return "recipe/imageuploadform";
    }
    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){ // this is going to handle the upload of the image
        imageService.saveImageFile(Long.valueOf(id), file);
        log.debug("we submit");
        return "redirect:/recipe/" + id +"/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));

        byte[] byteArray = new byte[recipeCommand.getImage().length];

        int i = 0;

        for(Byte wrappedByte : recipeCommand.getImage()){
            byteArray[i++] = wrappedByte;
        }

        response.setContentType("image/jpg"); // we create an InputStream and IOUtils is going to copy from the byte array input stream to the response output stream.
        InputStream is = new ByteArrayInputStream(byteArray);
        IOUtils.copy(is, response.getOutputStream()); // we are asking Spring MVC to give us the HttpServletResponse
    }
}
