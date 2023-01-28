package com.zanchenko.alexey.spring5recipeapp.controllers;

import com.zanchenko.alexey.spring5recipeapp.commands.RecipeCommand;
import com.zanchenko.alexey.spring5recipeapp.services.ImageService;
import com.zanchenko.alexey.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {
    @Mock // we have 2 mocks
    ImageService imageService;
    @Mock
    RecipeService recipeService;

    ImageController controller; // we will get injected into the controller
    // we do dat manually because we are running true unit tests
    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        controller = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getImageForm() throws Exception {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        when(recipeService.findCommandById(anyLong())).thenReturn(command); // when we do findById any Long value return back that command

        // when
        mockMvc.perform(get("/recipe/1/image")) // we're going to test out Spring MVC. We're going to perform a get action. And we're performing a get and we're going to say status is ok and we're going to verify that on line 46 there tht the model attribute doe exist
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void handleImagePost() throws Exception{ // we're going to test, actually uploading the image file
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/recipe/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());
    }

    @Test
    public void renderImageFromDB() throws Exception {

        //given
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()){
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        //when
        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] reponseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, reponseBytes.length);
    }
}