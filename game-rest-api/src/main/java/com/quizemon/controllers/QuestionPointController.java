package com.quizemon.controllers;

import com.arangodb.model.AqlQueryOptions;
import com.quizemon.arangoentities.QuestionPointDao;
import com.quizemon.arangorepositories.QuestionPointRepo;
import com.quizemon.types.QuestionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/questionpoints")
public class QuestionPointController {

  @Autowired
  QuestionPointRepo questionPointRepo;

  /**
   * Fetch question-points within an area
   * @param   lat           Latitude in WGS84 format. Latitude goes from -90 to 90 and gives the north/south position.
   * @param   lng           Longitude in WGS84 format. Longitude goes from -180 to 180 and gives the west/east position.
   * @param   distance      Distance (in meters) within search for question-points is done. Defaults to 1000 if not provided.
   * @return                All questionpoint within distance from the location provided with lat and lng
   * @See     QuestionPoint
   */
  @GetMapping
  public List<QuestionPoint> get(@RequestParam(required = false) Double lat, @RequestParam(required = false) Double lng,
                                 @RequestParam(value = "distance", required = false, defaultValue = "1000") int distance) {

    Stream<QuestionPointDao> questionPointStream = null;
    if(lat != null && lng != null) {
      questionPointStream = StreamSupport.stream(questionPointRepo.findWithinDistance(lat, lng, distance).spliterator(), true);
    } else {
      //If no coordinates are given, we return the first 100 points we find.
      questionPointStream = StreamSupport.stream(questionPointRepo.findAll(new PageRequest(0, 100)).spliterator(), true);
    }

    return questionPointStream.map(q -> q.toResponseType()).collect(Collectors.toList());
  }

}
