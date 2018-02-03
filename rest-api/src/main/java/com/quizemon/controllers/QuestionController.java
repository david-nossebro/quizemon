package com.quizemon.controllers;

import com.quizemon.ResponseExceptions.NotFoundException;
import com.quizemon.arangoentities.QuestionDAO;
import com.quizemon.arangorepositories.QuestionRepository;
import com.quizemon.types.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping
    public List<Question> get() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(q -> questions.add(q.toResponseType()));
        return questions;
    }

    @GetMapping(path = "/{id}")
    public Question get(@PathVariable String id) {
        QuestionDAO q = questionRepository.findOne(id);
        if(q != null) {
            return q.toResponseType();
        }
        throw new NotFoundException();
    }

    @PostMapping
    public Question post(@RequestBody @Valid Question question) {
        return questionRepository.save(question.toDao()).toResponseType();
    }

    @PutMapping(path = "/{id}")
    public Question put(@PathVariable String id, @RequestBody @Valid Question question) {
        if(!questionRepository.exists(id)) {
            throw new NotFoundException();
        }

        question.setId(id);
        return questionRepository.save(question.toDao()).toResponseType();
    }

    @PutMapping
    public Question put(@RequestBody @Valid Question question) {
        if(!questionRepository.exists(question.getId())) {
            throw new NotFoundException();
        }

        return questionRepository.save(question.toDao()).toResponseType();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        if(!questionRepository.exists(id)) {
            throw new NotFoundException();
        }

        questionRepository.delete(id);
    }

    @DeleteMapping()
    public void delete(@RequestBody Question question) {
        if(!questionRepository.exists(question.getId())) {
            throw new NotFoundException();
        }

        questionRepository.delete(question.getId());
    }
}
