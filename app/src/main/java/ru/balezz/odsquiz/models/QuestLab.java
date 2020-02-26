package ru.balezz.odsquiz.models;

import java.util.List;

public class QuestLab {
    private static final QuestLab ourInstance = new QuestLab();
    private List<Quest> mQuests;

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

