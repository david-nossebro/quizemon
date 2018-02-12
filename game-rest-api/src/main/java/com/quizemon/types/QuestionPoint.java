package com.quizemon.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionPoint {

  /**
   * A unique identyfier
   */
  private String id;

  /**
   * Latitude given in format WGS84. Latutude goes from -90 to 90 and gives the north/south position.
   */
  private Double latitude;

  /**
   * Longitude given in format WGS84. Longitude goes from -180 to 180 and gives the west/east position.
   */
  private Double longitude;

}
