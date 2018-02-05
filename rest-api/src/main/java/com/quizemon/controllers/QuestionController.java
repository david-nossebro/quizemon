package com.quizemon.controllers;

import com.quizemon.ResponseExceptions.NotFoundException;
import com.quizemon.arangoentities.QuestionDao;
import com.quizemon.arangorepositories.QuestionRepo;
import com.quizemon.types.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/questions")
public class QuestionController {

  @Autowired
  private QuestionRepo questionRepo;

  @GetMapping
  public List<Question> get() {
    List<Question> questions = new ArrayList<>();
    questionRepo.findAll().forEach(q -> questions.add(q.toResponseType()));
    return questions;
  }

  @GetMapping(path = "/{id}")
  public Question get(@PathVariable String id) {
    QuestionDao q = questionRepo.findOne(id);
    if (q != null) {
      return q.toResponseType();
    }
    throw new NotFoundException();
  }

  @PostMapping
  public Question post(@RequestBody @Valid Question question) {
    return questionRepo.save(question.toDao()).toResponseType();
  }

  @PutMapping(path = "/{id}")
  public Question put(@PathVariable String id, @RequestBody @Valid Question question) {
    if (!questionRepo.exists(id)) {
      throw new NotFoundException();
    }

    question.setId(id);
    return questionRepo.save(question.toDao()).toResponseType();
  }

  @PutMapping
  public Question put(@RequestBody @Valid Question question) {
    if (!questionRepo.exists(question.getId())) {
      throw new NotFoundException();
    }

    return questionRepo.save(question.toDao()).toResponseType();
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    if (!questionRepo.exists(id)) {
      throw new NotFoundException();
    }

    questionRepo.delete(id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping()
  public void delete(@RequestBody Question question) {
    if (!questionRepo.exists(question.getId())) {
      throw new NotFoundException();
    }

    questionRepo.delete(question.getId());
  }
}
