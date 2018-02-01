package com.quizemon.controllers;

import com.quizemon.arangorepositories.QuestionRepository;
import com.quizemon.types.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<Question> get() {
        List<Question> questions = new ArrayList<>();
        questionRepository.findAll().forEach(q -> questions.add(q.toType()));
        return questions;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Question post(@Valid Question question) {
        return questionRepository.save(question.toDao()).toType();
    }
}
