package ru.balezz.odsquiz.models;

import java.util.ArrayList;
import java.util.List;

public class QuestLab {
    private static final QuestLab ourInstance = new QuestLab();
    private List<Quest> mQuests = new ArrayList<>();

    public static QuestLab getInstance() {
        return ourInstance;
    }

    private QuestLab() {
        Quest quest;
        quest = new Quest(AnswerType.Check, 4, new boolean[]{true, false, false, false});
        quest.setQuestion("Data Science - это ... ");
        quest.setChoice("Наука о программах.");
        quest.setChoice("Наука о данных.");
        quest.setChoice("Наука о науке.");
        quest.setChoice("Лженаука.");
        mQuests.add(quest);

        quest = new Quest(AnswerType.Radio, 4, new boolean[]{true, false, false, false});
        quest.setQuestion("В основе линейной регрессии лежит ");
        quest.setChoice("Метод наименьших квадратов.");
        quest.setChoice("Теорема о разложении функции в ряд Тейлора.");
        quest.setChoice("Теорема о разложении функции в ряд Маклорена.");
        quest.setChoice("Теорема о разложении функции в ряд Фурье.");
        mQuests.add(quest);

        quest = new Quest(AnswerType.Check, 4, new boolean[]{true, false, false, false});
        quest.setQuestion("В современных нейронных сетях используются алгоритмы ");
        quest.setChoice("Обратное распространение ошибки.");
        quest.setChoice("Сверточная фильтрация.");
        quest.setChoice("Оптимальная фильтрация.");
        quest.setChoice("Оптимизация нелинейной функции управления.");
        mQuests.add(quest);
    }

    public List<Quest> getQuests() {
        return mQuests;
    }
}

