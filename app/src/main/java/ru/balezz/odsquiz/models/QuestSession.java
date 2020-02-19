package ru.balezz.odsquiz.models;

import java.util.List;

public class QuestSession {
    private boolean[][] mUserChoices;

    public static QuestSession getInstance(List<Quest> quests) {
        return new QuestSession(quests);
    }

    private QuestSession(List<Quest> quests){
        mUserChoices = new boolean[quests.size()][];
        for (int i = 0; i < quests.size(); i++) {
            mUserChoices[i] = new boolean[quests.get(i).getChoiceCount()];
        }
    }

    public boolean getUserCheck(int questId, int index) {
        return mUserChoices[questId][index];
    }

    public void toggleUserCheck(int questId, int index) {
        mUserChoices[questId][index] = ! mUserChoices[questId][index];
    }
}
