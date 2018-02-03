package com.quizemon.arangoentities;

import com.arangodb.springframework.annotation.Document;
import com.quizemon.types.Question;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

@Document("questions")
public class QuestionDAO {

    @Id
    private String id;

    @NotNull
    private String question;

    //First alternative in array represent the correct answer, the other three are alternatives
    @Size(
            message = "A question must have 4 alternatives",
            min = 4,
            max = 4
    )
    private String[] alternatives;

    public QuestionDAO() {
        super();
    }

    public QuestionDAO(final String id, final String question, final String[] alternatives) {
        this.id = id;
        this.question = question;
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

    public void setQuestion(String quesiton) {
        this.question = quesiton;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(String[] alternatives) {
        this.alternatives = alternatives;
    }

    public Question toResponseType() {
        List<String> altList = Arrays.asList(alternatives);
        return new Question(id, question, altList.get(0), altList.subList(1, altList.size()));
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", quesiton='" + question + '\'' +
                ", alternatives=" + Arrays.toString(alternatives) +
                '}';
    }
}
