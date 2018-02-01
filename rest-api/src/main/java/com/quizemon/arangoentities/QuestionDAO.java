package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import com.quizemon.types.Question;
import org.springframework.data.annotation.Id;

import java.util.Arrays;

@Document("questions")
public class QuestionDAO {

    @Id
    private String id;
    private String quesiton;
    private String[] alternatives;

    public QuestionDAO() {
        super();
    }

    public QuestionDAO(final String quesiton, final String[] alternatives) {
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

    public Question toType() {
        return new Question(id, quesiton, alternatives);
    }
}
