package ru.balezz.odscards.models;

import java.util.ArrayList;
import java.util.List;

enum AnswerType {
    Check, Radio
}

public class Quest {
    String mQuestion;
    List<String> mAnswers;
    int mChoiceCount;
    boolean[] mRightAnswers;
    AnswerType mType;

    public Quest(AnswerType type, int choiceCount, boolean[] rightAnswer) {
        mType = type;
        mChoiceCount = choiceCount;
        mAnswers = new ArrayList<>();
        mRightAnswers = rightAnswer;
    }

    public int getChoiceCount() {
        return mChoiceCount;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getAnswers() {
        return mAnswers;
    }

    public void setChoice(String choice) {
        mAnswers.add(choice);
    }
}
