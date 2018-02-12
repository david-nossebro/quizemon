package com.quizemon.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {

  private String id;
  private String question;
  private List<String> alternatives;

}
