package ru.balezz.odsquiz.models;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Quest {
    private static int counter;
    private int mId;
    private String mQuestion;
    private List<String> mChoices;
    private int mChoiceCount;
    private BitSet mRightAnswers;
    private AnswerType mType;
    private String mExplanation;

    public Quest() {
        mType = AnswerType.Check;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoices() {
        return mChoices;
    }

    public AnswerType getType() {
        return mType;
    }

    public int getChoiceCount() {
        return mChoices.size() - 1;
    }

    public void setChoices(List<String> choices) {
        mChoices = choices;
    }

    public void setRightAnswers(BitSet rightAnswers) {
        mRightAnswers = rightAnswers;
    }

    public BitSet getRightAnswers() {
        return mRightAnswers;
    }

    public String getExplanation() {
        return mExplanation;
    }

    public void setExplanation(String explanation) {
        mExplanation = explanation;
    }
}
