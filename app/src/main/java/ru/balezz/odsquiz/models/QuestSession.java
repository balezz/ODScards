package ru.balezz.odsquiz.models;

import android.util.Log;

import java.util.List;

public class QuestSession {
    private static final String TAG = "QuestSession";
    private boolean[][] mUserChecks;
    private boolean[] mQuestIsAnswered;
    private boolean[] mQuestIsAnsweredRight;
    private int mSize;

    public static QuestSession getInstance(List<Quest> quests) {
        return new QuestSession(quests);
    }

    private QuestSession(List<Quest> quests){
        mSize = quests.size();
        mUserChecks = new boolean[mSize][];
        mQuestIsAnswered = new boolean[mSize];
        mQuestIsAnsweredRight = new boolean[mSize];
        for (int i = 0; i < quests.size(); i++) {
            mUserChecks[i] = new boolean[quests.get(i).getChoiceCount()];
        }
    }

    public boolean getUserCheck(int questId, int index) {
        return mUserChecks[questId][index];
    }

    public void toggleUserCheck(int questId, int index) {
        mUserChecks[questId][index] = ! mUserChecks[questId][index];
    }

    public void setSingleUserCheck(int questId, int index) {
        Log.d(TAG, "setSingleUserCheck: " + questId + ", " + index);
        for (int i = 0; i < mUserChecks[questId].length; i++) {
            mUserChecks[questId][i] = i == index - 1;
        }
    }

    public void setQuestIsAnswered(int questId) {
        mQuestIsAnswered[questId] = true;
    }

    public boolean isQuestAnswered(int questId) {
        return mQuestIsAnswered[questId];
    }
}
