package ru.balezz.odscards.models;

import java.util.ArrayList;
import java.util.List;

public class QuestLab {
    private static final QuestLab ourInstance = new QuestLab();
    private List<Quest> mQuests = new ArrayList<>();

    public static QuestLab getInstance() {
        return ourInstance;
    }

    private QuestLab() {
        Quest quest = new Quest(AnswerType.Radio, 4, new boolean[]{true, false, false, false});
        quest.setQuestion("Data Science - это ... ");
        quest.setAnswers(new String[]{"Наука о программах.", "Наука о данных.", "Наука о науке.",
        "Лженаука."});
        mQuests.add(quest);
    }

    public List<Quest> getQuests() {
        return mQuests;
    }
}
