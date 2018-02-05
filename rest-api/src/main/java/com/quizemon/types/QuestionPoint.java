package com.quizemon.types;

import com.quizemon.arangoentities.QuestionPointDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPoint {

  private String id;

  @NotNull(message = "You need to provide latitude")
  private Double latitude;

  @NotNull(message = "You need to provide longitude")
  private Double longitude;

  public QuestionPointDao toDao() {
    return new QuestionPointDao(id, latitude, longitude);
  }
}
