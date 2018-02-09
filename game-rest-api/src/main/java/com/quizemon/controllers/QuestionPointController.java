package com.quizemon.controllers;

import com.quizemon.arangoentities.QuestionPointDao;
import com.quizemon.arangorepositories.QuestionPointRepo;
import com.quizemon.types.QuestionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/questionpoints")
public class QuestionPointController {

  @Autowired
  QuestionPointRepo questionPointRepo;

  @GetMapping
  public List<QuestionPoint> get(@RequestParam(required = false) Double lat, @RequestParam(required = false) Double lng,
                           @RequestParam(value = "distance", required = false, defaultValue = "50") int distance) {

    Stream<QuestionPointDao> questionPointStream = null;
    if(lat != null && lng != null) {
      questionPointStream = StreamSupport.stream(
        questionPointRepo.findWithinDistance(lat, lng, distance).spliterator(), true);
    } else {
        questionPointStream = StreamSupport.stream(questionPointRepo.findAll().spliterator(), true);
    }

    //TODO: Should limit result from database instead
    return questionPointStream.map(q -> q.toResponseType()).limit(100).collect(Collectors.toList());
  }

}
