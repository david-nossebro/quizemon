package com.quizemon.controllers;

import com.quizemon.ResponseExceptions.NotFoundException;
import com.quizemon.arangorepositories.QuestionPointRepo;
import com.quizemon.types.QuestionPoint;
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
@RequestMapping("/questionpoints")
public class QuestionPointController {

  @Autowired
  QuestionPointRepo questionPointRepo;

  @GetMapping
  public List<QuestionPoint> get() {
    List<QuestionPoint> questionPoints = new ArrayList<>();
    questionPointRepo.findAll().forEach(q -> questionPoints.add(q.toResponseType()));
    return questionPoints;
  }

  @GetMapping("/{id}")
  public QuestionPoint get(@PathVariable String id) {
    if (!questionPointRepo.exists(id)) {
      throw new NotFoundException();
    }

    return questionPointRepo.findOne(id).toResponseType();
  }

  @PostMapping
  public QuestionPoint post(@Valid @RequestBody QuestionPoint questionPoint) {
    return questionPointRepo.save(questionPoint.toDao()).toResponseType();
  }

    /*
    @PostMapping
    public List<QuestionPoint> post(@Valid @RequestBody List<QuestionPoint> questionPoints) {
        List<QuestionPoint> response = new ArrayList<>();
        questionPoints.forEach(q -> response.add(post(q)));
        return response;
    }
    */

  @PutMapping("/{id}")
  public QuestionPoint put(@PathVariable String id, @Valid @RequestBody QuestionPoint questionPoint) {
    if (!questionPointRepo.exists(id)) {
      throw new NotFoundException();
    }

    questionPoint.setId(id);
    return questionPointRepo.save(questionPoint.toDao()).toResponseType();
  }

  @PutMapping
  public QuestionPoint put(@Valid @RequestBody QuestionPoint questionPoint) {
    if (!questionPointRepo.exists(questionPoint.getId())) {
      throw new NotFoundException();
    }

    return questionPointRepo.save(questionPoint.toDao()).toResponseType();
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    if (!questionPointRepo.exists(id)) {
      throw new NotFoundException();
    }

    questionPointRepo.delete(id);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping
  public void delete(@RequestBody QuestionPoint questionPoint) {
    if (!questionPointRepo.exists(questionPoint.getId())) {
      throw new NotFoundException();
    }

    questionPointRepo.delete(questionPoint.getId());
  }

}
