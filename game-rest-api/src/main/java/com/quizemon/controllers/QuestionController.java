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

import javax.validation.Valid;
import java.util.Objects;

@RestController
public class QuestionController {

  @Autowired
  private QuestionRepo questionRepo;

  /**
   * Fetch a random question
   * @return a Question
   * @see Question
   */
  @GetMapping("/randomquestion")
  public Question get() {
    return questionRepo.getRandom().toResponseType();
  }

  //TODO: Add test

  /**
   *
   * @param   id        The id of the question you want to give an answer to.
   * @param   response  QuestionResponse where you provide an answer
   * @return            A QuestionResponse saying if your answer was correct or not, and what the correct answer is.
   * @see     QuestionResponse
   */
  @PostMapping("questions/{id}/response")
  public QuestionResponse post(@PathVariable String id, @Valid @RequestBody QuestionResponse response) {
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
