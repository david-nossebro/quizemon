package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import com.quizemon.types.QuestionPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

//TODO: Fix GeoIndex

@Document("questionpoints")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPointDao {

  @Id
  private String id;

  @NotNull
  private Double latitude;

  @NotNull
  private Double longitude;

  public QuestionPoint toResponseType() {
    return new QuestionPoint(id, latitude, longitude);
  }
}
