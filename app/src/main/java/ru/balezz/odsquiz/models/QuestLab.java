package ru.balezz.odsquiz.models;

import java.util.ArrayList;
import java.util.List;

import ru.balezz.odsquiz.utils.QuestsFetchr;

public class QuestLab {
    private static final QuestLab ourInstance = new QuestLab();
    private List<Quest> mQuests = new ArrayList<>();

    public static QuestLab getInstance() {
        return ourInstance;
    }

    private QuestLab() {
    }

    public void setQuests(List<Quest> quests) {
        mQuests = quests;
    }

    public List<Quest> getQuests() {
        return mQuests;
    }
}

