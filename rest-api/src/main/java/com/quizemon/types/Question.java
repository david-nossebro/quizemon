package com.quizemon.types;

import com.quizemon.arangoentities.QuestionDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public @Data
class Question {

  private String id;

  @NotNull(message = "You must provide a question in the request")
  private String question;

  @NotNull(message = "You must provide an answer in the request")
  private String answer;

  @NotNull(message = "You must provide 3 alternatives")
  @Size(
    min = 3,
    max = 3,
    message = "You must provide 3 alternatives"
  )
  private List<String> alternatives;

  public QuestionDao toDao() {

    String[] alternativesArray = new String[4];
    alternativesArray[0] = answer;
    for (int i = 0; i < 3; i++) {
      alternativesArray[i + 1] = alternatives.get(i);
    }

    return new QuestionDao(id, question, alternativesArray);
  }
}
