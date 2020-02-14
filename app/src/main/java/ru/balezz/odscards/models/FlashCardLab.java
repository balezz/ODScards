package ru.balezz.odscards.models;

import java.util.ArrayList;
import java.util.List;

public class FlashCardLab {
    private static final FlashCardLab ourInstance = new FlashCardLab();
    List<FlashCard> mFlashCards;

    public static FlashCardLab getInstance() {
        return ourInstance;
    }

    private FlashCardLab() {
        mFlashCards = new ArrayList<>();
        FlashCard fc = new FlashCard();
        fc.setQuestion("Датасаентист самая крутая в мире профессия.");
        fc.setAnswer("Верно. Помимо программирования датасаентист должен разбираться в науке, " +
                "и это очень круто.");
        mFlashCards.add(fc);
    }
}
