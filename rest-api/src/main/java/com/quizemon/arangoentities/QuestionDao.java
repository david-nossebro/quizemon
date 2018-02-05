package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import com.quizemon.types.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Document("questions")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionDao {

  @Id
  private String id;

  @NotNull
  private String question;

  //First alternative in array represent the correct answer, the other three are alternatives
  @Size(
    message = "A question must have 4 alternatives",
    min = 4,
    max = 4
  )
  private String[] alternatives;

  public Question toResponseType() {
    List<String> altList = Arrays.asList(alternatives);
    return new Question(id, question, altList.get(0), altList.subList(1, altList.size()));
  }

  @Override
  public String toString() {
    return "Question{" +
      "id='" + id + '\'' +
      ", quesiton='" + question + '\'' +
      ", alternatives=" + Arrays.toString(alternatives) +
      '}';
  }
}
