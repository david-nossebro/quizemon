package com.quizemon.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class QuestionControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/questions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"question\":\"What color is the sun?\",\"alternatrives\":[{\"alternative\":\"yellow\",\"correct\":true},{\"alternative\":\"blue\",\"correct\":false},{\"alternative\":\"red\",\"correct\":false},{\"alternative\":\"black\",\"correct\":false}]},{\"question\":\"What is the currency in Sweden?\",\"alternatrives\":[{\"alternative\":\"Swedish Crown\",\"correct\":true},{\"alternative\":\"Dollar\",\"correct\":false},{\"alternative\":\"Euro\",\"correct\":false},{\"alternative\":\"Norweigan Crown\",\"correct\":false}]}]")));
    }
}
