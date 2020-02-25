package ru.balezz.odsquiz.models;

import android.util.Log;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class QuestSession {
    private static final String TAG = "QuestSession";
    private static QuestSession ourInstance;
    private boolean[][] mUserChecks;
    private boolean[] mQuestIsAnswered;
    private int mCurrentId;
    private int mRightAnswerCount;
    private int mWrongAnswerCount;

    public static QuestSession getInstance(List<Quest> quests) {
        if (ourInstance == null) {
            ourInstance = new QuestSession(quests);
        }
        return ourInstance;
    }

    private QuestSession(List<Quest> quests){
        int size = quests.size();
        mUserChecks = new boolean[size][];
        mQuestIsAnswered = new boolean[size];
        for (int i = 0; i < quests.size(); i++) {
            mUserChecks[i] = new boolean[quests.get(i).getChoiceCount()];
        }
    }

    public int getCurrentId() {
        return mCurrentId;
    }

    public void setCurrentId(int currentId) {
        mCurrentId = currentId;
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

    // todo
    public boolean checkAnswerIsRight(int questId) {
        BitSet rightAnswers = QuestLab.getInstance().getQuests().get(questId).getRightAnswers();
        Log.d(TAG, "checkAnswerIsRight: rightAnswers: " + rightAnswers.toString());
        Log.d(TAG, "checkAnswerIsRight: checkedAnswers: " + Arrays.toString(mUserChecks[questId]));
        for (int i = 0; i < mUserChecks[questId].length; i++) {
            if (mUserChecks[questId][i] != rightAnswers.get(i))
                return false;
        }
        return true;
    }

    public void incrementWrong() {
        mWrongAnswerCount++;
    }

    public void incrementRight() {
        mRightAnswerCount++;
    }

    public String getRightCount() {
        return String.valueOf(mRightAnswerCount);
    }

    public String getWrongCount() {
        return String.valueOf(mWrongAnswerCount);
    }
}
