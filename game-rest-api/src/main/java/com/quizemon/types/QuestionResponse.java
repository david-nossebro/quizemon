package com.quizemon.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionResponse {

  private String id;
  private String answer;
  private Boolean correct;
  private String correctAnswer;
}
