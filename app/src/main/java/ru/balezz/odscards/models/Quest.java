package ru.balezz.odscards.models;

enum AnswerType {
    Check, Radio
}

public class Quest {
    String mQuest;
    String [] mAnswers;
    int mRightAnswerIndex;
    AnswerType mType;

    public Quest(AnswerType type, int answerVariants, int rightAnswer) {
        mType = type;
        mAnswers = new String[answerVariants];
        mRightAnswerIndex = rightAnswer;
    }

    public String getQuest() {
        return mQuest;
    }

    public void setQuest(String quest) {
        mQuest = quest;
    }

    public String[] getAnswers() {
        return mAnswers;
    }

    public void setAnswers(String[] answers) {
        mAnswers = answers;
    }
}
