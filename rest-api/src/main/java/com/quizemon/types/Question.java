package com.quizemon.types;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String question;
    private List<Alternative> alternatrives;

    public Question(String question, String correctAlternative, String... wrongAlternatives) {
        this.question = question;
        this.alternatrives = new ArrayList<>();
        this.alternatrives.add(new Alternative(correctAlternative, true));
        for (String wrongAlternative : wrongAlternatives) {
            this.alternatrives.add(new Alternative(wrongAlternative, false));
        }
    }

    public String getQuestion() {
        return question;
    }

    public List<Alternative> getAlternatrives() {
        return alternatrives;
    }

    public class Alternative {

        private String alternative;
        private boolean correct;

        public Alternative(String alternative, boolean correct) {
            this.alternative = alternative;
            this.correct = correct;
        }

        public String getAlternative() {
            return alternative;
        }

        public boolean isCorrect() {
            return correct;
        }
    }
}
