package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import com.quizemon.types.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Document("questions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDao {

  @Id
  private String id;

  private String question;

  private String[] alternatives;

  public Question toResponseType() {
    List<String> altList = Arrays.asList(alternatives);

    //The list is shuffled, otherwise the first alternative will always be the correct one.
    Collections.shuffle(altList);

    return new Question(id, question, altList);
  }

  public String getCorrectAnswer() {
    return alternatives[0];
  }
}
