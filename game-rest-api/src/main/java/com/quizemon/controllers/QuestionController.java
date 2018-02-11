package com.quizemon.controllers;

import com.quizemon.ResponseExceptions;
import com.quizemon.arangoentities.QuestionDao;
import com.quizemon.arangorepositories.QuestionRepo;
import com.quizemon.types.Question;
import com.quizemon.types.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class QuestionController {

  @Autowired
  private QuestionRepo questionRepo;

  @GetMapping("/randomquestion")
  public Question get() {
    return questionRepo.getRandom().toResponseType();
  }

  @PostMapping("questions/{id}/response")
  public QuestionResponse post(@PathVariable String id, @RequestBody QuestionResponse response) {
    QuestionDao questionDao = questionRepo.findOne(id);
    if(questionDao == null) {
      throw new ResponseExceptions.NotFoundException();
    }

    String correctAnswer = questionDao.getCorrectAnswer();

    response.setId(questionDao.getId());
    response.setCorrectAnswer(correctAnswer);
    response.setCorrect(Objects.equals(response.getAnswer(), correctAnswer));

    return response;
  }
}
