package ru.balezz.odscards.models;

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
    private boolean[] mUserAnswers;

    Quest(AnswerType type, int choiceCount, boolean[] rightAnswers) {
        id = counter++;
        mType = type;
        mChoiceCount = choiceCount;
        mChoices = new ArrayList<>();
        mRightAnswers = rightAnswers;
        mUserAnswers = new boolean[choiceCount];
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

    public void setUserAnswer(int i, boolean answer) {
        if (i < mChoiceCount){
            mUserAnswers[i] = answer;
        }
    }

    public void toggleUserAnswerCheck(int index) {
        mUserAnswers[index] = !mUserAnswers[index];
    }

    public void setSingleUserCheck(int index) {
        mUserAnswers = new boolean[mChoiceCount];
        mUserAnswers[index] = true;
    }

    public boolean getUserCheck(int index) {
        return mUserAnswers[index];
    }

    void setQuestion(String question) {
        mQuestion = question;
    }

    void setChoice(String choice) {
        mChoices.add(choice);
    }


}
