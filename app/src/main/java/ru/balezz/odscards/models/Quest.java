package ru.balezz.odscards.models;

enum AnswerType {
    Check, Radio
}

public class Quest {
    String mQuestion;
    String [] mAnswers;
    int mRightAnswerIndex;
    AnswerType mType;

    public Quest(AnswerType type, int answerVariants, int rightAnswer) {
        mType = type;
        mAnswers = new String[answerVariants];
        mRightAnswerIndex = rightAnswer;
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
