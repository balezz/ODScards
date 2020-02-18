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
    private boolean[] mUserChecks;

    Quest(AnswerType type, int choiceCount, boolean[] rightAnswers) {
        id = counter++;
        mType = type;
        mChoiceCount = choiceCount;
        mChoices = new ArrayList<>();
        mRightAnswers = rightAnswers;
        mUserChecks = new boolean[choiceCount];
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

    public void setUserCheck(int i, boolean isChecked) {
        if (i < mChoiceCount){
            mUserChecks[i] = isChecked;
        }
    }

    public void toggleUserCheck(int index) {
        mUserChecks[index] = !mUserChecks[index];
    }

    public void setSingleUserCheck(int index) {
        mUserChecks = new boolean[mChoiceCount];
        mUserChecks[index] = true;
    }

    public boolean getUserCheck(int index) {
        return mUserChecks[index];
    }

    void setQuestion(String question) {
        mQuestion = question;
    }

    void setChoice(String choice) {
        mChoices.add(choice);
    }


}
