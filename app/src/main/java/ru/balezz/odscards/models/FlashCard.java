package ru.balezz.odscards.models;

public class FlashCard {
    static int counter;
    int id;
    String mQuestion;
    String mAnswer;

    FlashCard() {
        this.id = counter++;
    }

    public int getId() {
        return id;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setAnswer(String answer) {
        mAnswer = answer;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }


}
