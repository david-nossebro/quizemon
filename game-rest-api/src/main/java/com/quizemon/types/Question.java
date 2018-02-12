package com.quizemon.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {

  /**
   * A unique identyfier
   */
  private String id;

  /**
   * A question
   */
  private String question;

  /**
   * Four alternatives to choose from
   */
  private List<String> alternatives;

}
