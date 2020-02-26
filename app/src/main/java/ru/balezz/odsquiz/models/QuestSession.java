package ru.balezz.odsquiz.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class QuestSession {
    private static final String TAG = "QuestSession";
    private static QuestSession ourInstance;
    private List<Quest> mQuests;
    private List<BitSet> mUserChecks;
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
        mQuests = quests;
        mQuestIsAnswered = new boolean[quests.size()];
        mUserChecks = new ArrayList<BitSet>(quests.size());
        for (int i = 0; i < quests.size(); i++) {
            mUserChecks.add(new BitSet());
        }
    }

    public int getCurrentId() {
        return mCurrentId;
    }

    public void setCurrentId(int currentId) {
        mCurrentId = currentId;
    }

    public boolean getUserCheck(int questId, int index) {
        return mUserChecks.get(questId).get(index);
    }

    public void toggleUserCheck(int questId, int index) {
        mUserChecks.get(questId).flip(index);
    }

    public void setSingleUserCheck(int questId, int index) {
        Log.d(TAG, "setSingleUserCheck: " + questId + ", " + index);
        mUserChecks.get(questId).clear();
        mUserChecks.get(questId).set(index);
        Log.d(TAG, "setSingleUserCheck: " + mUserChecks.get(questId).toString());
    }

    public void setQuestIsAnswered(int questId) {
        mQuestIsAnswered[questId] = true;
    }

    public boolean isQuestAnswered(int questId) {
        return mQuestIsAnswered[questId];
    }


    public boolean checkAnswerIsRight(int questId) {
        BitSet rightAnswers = mQuests.get(questId).getRightAnswers();
        Log.d(TAG, "checkAnswerIsRight: rightAnswers: " + rightAnswers.toString());
        Log.d(TAG, "checkAnswerIsRight: checkedAnswers: " + mUserChecks.get(questId).toString());
        return mUserChecks.get(questId).equals(rightAnswers);
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
