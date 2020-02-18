package ru.balezz.odscards.models;

enum AnswerType {
    Check, Radio
}

public class Quest {
    String mQuestion;
    String [] mAnswers;
    int mChoiceCount;
    boolean[] mRightAnswers;
    AnswerType mType;

    public Quest(AnswerType type, int choiceCount, boolean[] rightAnswer) {
        mType = type;
        mChoiceCount = choiceCount;
        mAnswers = new String[choiceCount];
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

    public String[] getAnswers() {
        return mAnswers;
    }

    public void setAnswers(String[] answers) {
        mAnswers = answers;
    }
}
