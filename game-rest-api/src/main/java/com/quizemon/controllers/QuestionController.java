package com.quizemon.controllers;

import com.quizemon.arangorepositories.QuestionRepo;
import com.quizemon.types.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/randomquestion")
public class QuestionController {

  @Autowired
  private QuestionRepo questionRepo;

  @GetMapping
  public Question get() {
    return questionRepo.getRandom().toResponseType();
  }
}
