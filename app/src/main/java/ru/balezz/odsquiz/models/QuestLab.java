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

    }

    public List<Quest> getQuests() {
        return mQuests;
    }
}

