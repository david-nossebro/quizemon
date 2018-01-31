package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import org.springframework.data.annotation.Id;

import java.util.Arrays;

@Document("questions")
public class Question {

    @Id
    private String id;
    private String quesiton;
    private String[] alternatives;

    public Question() {
        super();
    }

    public Question(final String quesiton, final String[] alternatives) {
        this.quesiton = quesiton;
        this.alternatives = alternatives;
    }

    public String getId() {
        return id;
    }

    public String getQuesiton() {
        return quesiton;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", quesiton='" + quesiton + '\'' +
                ", alternatives=" + Arrays.toString(alternatives) +
                '}';
    }
}
