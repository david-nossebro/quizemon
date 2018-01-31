package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

@Document("questions")
public class Question {

    @Id
    private String id;

    private String question;

    public Question() {
        super();
    }

    public Question(final String question) {
        super();
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                '}';
    }
}
