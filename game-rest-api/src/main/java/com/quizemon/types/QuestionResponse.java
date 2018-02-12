package com.quizemon.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionResponse {

  /**
   * The unique id of the question the response was for
   */
  private String id;

  /**
   * Users answer to the question
   */
  @NotNull
  private String answer;

  /**
   * Boolean saying if the given answer was correct
   */
  private Boolean correct;

  /**
   * The correct answer to the question
   */
  private String correctAnswer;
}
