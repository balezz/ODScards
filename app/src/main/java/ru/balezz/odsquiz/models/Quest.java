package ru.balezz.odsquiz.models;

import java.util.ArrayList;
import java.util.List;

public class Quest {
    private static int counter;
    private int id;
    private String mQuestion;
    private List<String> mChoices;
    private int mChoiceCount;
    private boolean[] mRightAnswers;
    private AnswerType mType;
    private String mExplanation;

    Quest(AnswerType type, int choiceCount, boolean[] rightAnswers) {
        id = counter++;
        mType = type;
        mChoiceCount = choiceCount;
        mChoices = new ArrayList<>();
        mRightAnswers = rightAnswers;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoices() {
        return mChoices;
    }

    public AnswerType getType() {
        return mType;
    }

    public int getChoiceCount() {
        return mChoiceCount;
    }

    void setQuestion(String question) {
        mQuestion = question;
    }

    void setChoice(String choice) {
        mChoices.add(choice);
    }

    public boolean[] getRightAnswers() {
        return mRightAnswers;
    }

    public String getExplanation() {
        return mExplanation;
    }

    public void setExplanation(String explanation) {
        mExplanation = explanation;
    }
}
