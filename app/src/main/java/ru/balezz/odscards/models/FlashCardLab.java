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
        FlashCard fc;
        fc = new FlashCard();
        fc.setQuestion("Датасаентист самая крутая в мире профессия.");
        fc.setAnswer("Верно. Помимо программирования датасаентист должен разбираться в науке.");
        mFlashCards.add(fc);
        fc = new FlashCard();
        fc.setQuestion("Датасаентист тратит 80% времени на подготовку данных.");
        fc.setAnswer("Верно. Остальные 20% на нытье об этом.");
        mFlashCards.add(fc);
        fc = new FlashCard();
        fc.setQuestion("Дата иженер лучше дата саентиста.");
        fc.setAnswer("Неверно. Просто они решают разные задачи.");
        mFlashCards.add(fc);

    }

    public List<FlashCard> getFlashCards() {
        return mFlashCards;
    }
}
