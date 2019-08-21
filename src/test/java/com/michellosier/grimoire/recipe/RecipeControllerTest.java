package com.michellosier.grimoire.recipe;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String basePath = "/api/v1/recipe";

    @Test
    public void whenGetRoot_thenReturnAll() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(basePath).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenPartialNameParam_whenGetRoot_thenReturnSet() throws Exception {
        final String nameFragment = "ade";
        final String URL = String.format("%s?name=%s", basePath,nameFragment);
        mvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenInvalidTypeIdOnPath_whenRoot_thenReturnBadRequest() throws Exception {
        final String invalidId = "k";
        final String URL = String.format("%s/%s", basePath,invalidId);
        mvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenIdThatDoesNotExist_whenRoot_thenReturnNotFound() throws Exception {
        final int nonExistId = 999;
        final String URL = String.format("%s/%d", basePath, nonExistId);
        mvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


}
