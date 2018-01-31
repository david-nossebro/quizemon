package com.quizemon.controllers;

import com.google.common.collect.Lists;
import com.quizemon.arangoentities.Question;
import com.quizemon.arangorepositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {


    @Autowired
    private QuestionRepository questionRepository;


    @RequestMapping(method = RequestMethod.GET)
    public List<Question> get() {
        return Lists.newArrayList(questionRepository.findAll());

        /*
        questions.add(new Question("What color is the sun?", "yellow",
                "blue", "red", "black"));
        questions.add(new Question("What is the currency in Sweden?", "Swedish Crown",
                "Dollar", "Euro", "Norweigan Crown"));
        */
    }

}
