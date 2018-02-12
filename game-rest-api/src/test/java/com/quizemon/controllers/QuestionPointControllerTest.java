package com.quizemon.controllers;

import com.quizemon.arangoentities.QuestionDao;
import com.quizemon.arangoentities.QuestionPointDao;
import com.quizemon.arangorepositories.QuestionPointRepo;
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
public class QuestionPointControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  QuestionPointRepo questionPointRepo;

  @Test
  public void getQuestionPoints() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/questionpoints").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  public void getQuestionPointsWithinDistance() throws Exception {

    QuestionPointDao questionPointDao = questionPointRepo.save(new QuestionPointDao(null, 12.000000, 12.000000));

    mvc.perform(MockMvcRequestBuilders.get("/questionpoints")
      .param("lat", "12.000000")
      .param("lng", "12.000000")
      .param("distance", "1000")
      .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  //TODO: Add tests for PUT
  //TODO: Add tests for DELETE
}
