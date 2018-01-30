package com.quizemon.controllers;

import com.quizemon.types.Question;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Question> get() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What color is the sun?", "yellow",
                "blue", "red", "black"));
        questions.add(new Question("What is the currency in Sweden?", "Swedish Crown",
                "Dollar", "Euro", "Norweigan Crown"));
        return questions;
    }

    
}
