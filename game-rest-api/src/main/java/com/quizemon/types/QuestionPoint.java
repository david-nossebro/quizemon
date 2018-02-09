package com.quizemon.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPoint {

  private String id;
  private Double latitude;
  private Double longitude;

}
