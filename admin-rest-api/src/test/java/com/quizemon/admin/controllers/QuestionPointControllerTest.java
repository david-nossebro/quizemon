package com.quizemon.admin.controllers;

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

  @Test
  public void getQuestionPoints() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/questionpoints").accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());
  }

  @Test
  public void postQuestionPoint() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("latitude", 45.134563);
    jsonObject.put("longitude", 35.121682);

    mvc.perform(MockMvcRequestBuilders.post("/questionpoints")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().json(jsonObject.toString()));
  }

  @Test
  public void postQuestionWithoutLatitude() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("longitude", 35.121682);

    mvc.perform(MockMvcRequestBuilders.post("/questionpoints")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

  @Test
  public void postQuestionWithoutLongitude() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("latitude", 45.134563);

    mvc.perform(MockMvcRequestBuilders.post("/questionpoints")
      .content(jsonObject.toString())
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isBadRequest());
  }

    /*
    @Test
    public void postQuestionWithWrongCoordinateFormat() throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("latitude", 454.134563);
        jsonObject.put("longitude", 352.121682);

        mvc.perform(MockMvcRequestBuilders.post("/questionpoints")
                .content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    */


  //TODO: Add tests for PUT
  //TODO: Add tests for DELETE
}
