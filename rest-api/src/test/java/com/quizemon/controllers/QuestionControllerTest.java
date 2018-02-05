package com.quizemon.controllers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getQuestions() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/questions").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  public void postQuestion() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("question", "What color is the sun?");
    jsonObject.put("answer", "Yellow");
    JSONArray alternatives = new JSONArray();
    alternatives.put("Green");
    alternatives.put("Blue");
    alternatives.put("Black");
    jsonObject.put("alternatives", alternatives);

    mvc.perform(MockMvcRequestBuilders.post("/questions")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().json(jsonObject.toString()));
  }

  @Test
  public void postQuestionWithoutQuestion() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("answer", "Yellow");
    JSONArray alternatives = new JSONArray();
    alternatives.put("Green");
    alternatives.put("Blue");
    alternatives.put("Black");
    jsonObject.put("alternatives", alternatives);

    mvc.perform(MockMvcRequestBuilders.post("/questions")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void postQuestionWithoutAnswer() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("question", "What color is the sun?");
    JSONArray alternatives = new JSONArray();
    alternatives.put("Green");
    alternatives.put("Blue");
    alternatives.put("Black");
    jsonObject.put("alternatives", alternatives);

    mvc.perform(MockMvcRequestBuilders.post("/questions")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void postQuestionWithoutAlternatives() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("question", "What color is the sun?");
    jsonObject.put("answer", "Yellow");

    mvc.perform(MockMvcRequestBuilders.post("/questions")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void postQuestionWithOnlyOneAlternative() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("question", "What color is the sun?");
    jsonObject.put("answer", "Yellow");
    JSONArray alternatives = new JSONArray();
    alternatives.put("Green");
    jsonObject.put("alternatives", alternatives);

    mvc.perform(MockMvcRequestBuilders.post("/questions")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }


  //TODO: Add tests for PUT
  //TODO: Add tests for DELETE
}
