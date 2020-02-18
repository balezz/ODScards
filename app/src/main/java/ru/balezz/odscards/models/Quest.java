package ru.balezz.odscards.models;

import java.util.ArrayList;
import java.util.List;

enum AnswerType {
    Check, Radio
}

public class Quest {
    private static int counter;
    private int id;
    private String mQuestion;
    private List<String> mChoices;
    private int mChoiceCount;
    private boolean[] mRightAnswers;
    private AnswerType mType;

    Quest(AnswerType type, int choiceCount, boolean[] rightAnswer) {
        id = counter++;
        mType = type;
        mChoiceCount = choiceCount;
        mChoices = new ArrayList<>();
        mRightAnswers = rightAnswer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoices() {
        return mChoices;
    }

    void setQuestion(String question) {
        mQuestion = question;
    }

    void setChoice(String choice) {
        mChoices.add(choice);
    }
}
