package ru.balezz.odsquiz.models;

import android.util.Log;

import java.util.Arrays;
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
            if (i == (index))
                mUserChecks[questId][i] = true;
            else
                mUserChecks[questId][i] = false;
        }
        Log.d(TAG, "setSingleUserCheck: " + Arrays.toString(mUserChecks[questId]));
    }

    public void setQuestIsAnswered(int questId) {
        mQuestIsAnswered[questId] = true;
    }

    public boolean isQuestAnswered(int questId) {
        return mQuestIsAnswered[questId];
    }

    public boolean checkAnswerIsRight(int questId) {
        boolean[] rightAnswers = QuestLab.getInstance().getQuests().get(questId).getRightAnswers();
        Log.d(TAG, "checkAnswerIsRight: rightAnswers: " + Arrays.toString(rightAnswers));
        Log.d(TAG, "checkAnswerIsRight: checkedAnswers: " + Arrays.toString(mUserChecks[questId]));
        for (int i = 0; i < mUserChecks[questId].length; i++) {
            if (mUserChecks[questId][i] != rightAnswers[i])
                return false;
        }
        return true;
    }
}
