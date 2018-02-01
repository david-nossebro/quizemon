package com.quizemon.types;

import com.quizemon.arangoentities.QuestionDAO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private String id;

    @NotNull(message = "You must provide a question in the request")
    private String question;

    @NotNull(message = "You must provide 4 alternatives")
    @Size(
            min = 4,
            max = 4,
            message = "You must provide 4 alternatives"
    )
    private List<Alternative> alternatrives;

    public Question() {
    }

    public Question(String id, String question, String correctAlternative, String... wrongAlternatives) {
        this.id = id;
        this.question = question;
        this.alternatrives = new ArrayList<>();
        this.alternatrives.add(new Alternative(correctAlternative, true));
        for (String wrongAlternative : wrongAlternatives) {
            this.alternatrives.add(new Alternative(wrongAlternative, false));
        }
    }

    public Question(String id, String question, String[] alternatives) {
        this.id = id;
        this.question = question;
        this.alternatrives = new ArrayList<>();
        this.alternatrives.add(new Alternative(alternatives[0], true));

        for(int i=1; i<alternatives.length; i++) {
            this.alternatrives.add(new Alternative(alternatives[i], false));
        }
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

    public List<Alternative> getAlternatrives() {
        return alternatrives;
    }

    public void setAlternatrives(List<Alternative> alternatrives) {
        this.alternatrives = alternatrives;
    }

    public class Alternative {

        @NotNull(message = "An alternative needs to have an alternative")
        private String alternative;

        private boolean correct;

        public Alternative(String alternative, boolean correct) {
            this.alternative = alternative;
            this.correct = correct;
        }

        public String getAlternative() {
            return alternative;
        }

        public void setAlternative(String alternative) {
            this.alternative = alternative;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }
    }

    public QuestionDAO toDao() {

        String[] alternativesArray = new String[alternatrives.size()];
        Alternative correct = alternatrives.stream().filter(a -> a.correct).findFirst().get();
        alternatrives.remove(correct);
        alternativesArray[0] = correct.getAlternative();

        for(int i=0; i<alternatrives.size(); i++) {
            alternativesArray[i+1] = alternatrives.get(i).alternative;
        }

        return new QuestionDAO(question, alternativesArray);
    }
}
