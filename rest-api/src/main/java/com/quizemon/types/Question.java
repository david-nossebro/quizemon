package com.quizemon.types;

import com.quizemon.arangoentities.QuestionDAO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Question {

    private String id;

    @NotNull(message = "You must provide a question in the request")
    private String question;

    @NotNull(message = "You must provide an answer in the request")
    private String answer;


    @NotNull(message = "You must provide 3 alternatives")
    @Size(
            min = 3,
            max = 3,
            message = "You must provide 3 alternatives"
    )
    private List<String> alternatives;

    public Question() {
    }

    public Question(String id, String question, String answer, List<String> alternatives) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.alternatives = alternatives;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public QuestionDAO toDao() {

        String[] alternativesArray = new String[4];
        alternativesArray[0] = answer;
        for(int i=0; i<3; i++) {
            alternativesArray[i+1] = alternatives.get(i);
        }

        return new QuestionDAO(id, question, alternativesArray);
    }
}
