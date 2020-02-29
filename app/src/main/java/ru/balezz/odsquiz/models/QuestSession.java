package ru.balezz.odsquiz.models;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import ru.balezz.odsquiz.utils.QuestsLab;

public class QuestSession implements Serializable {
    private transient static final String SESSION_BACKUP = "QuestSession.save";
    private transient static final String TAG = "QuestSession";
    private transient static QuestSession ourInstance;
    private List<BitSet> mUserChecks;
    private boolean[] mQuestIsAnswered;
    private int mCurrentId;
    private int mRightAnswerCount;
    private int mWrongAnswerCount;
    private int mTotalLength;

    public static QuestSession getInstance(Activity activity) {
        int totalLength = QuestsLab.getInstance(activity)
                .getQuests().size();
        if (ourInstance == null) {
            if (!loadSession(activity)){
                ourInstance = new QuestSession(totalLength);
            }
        }
        return ourInstance;
    }

    public QuestSession(int totalLength) {
        mTotalLength = totalLength;
        mQuestIsAnswered = new boolean[totalLength];
        mUserChecks = new ArrayList<>(totalLength);
        for (int i = 0; i < totalLength; i++) {
            mUserChecks.add(new BitSet());
        }
    }

    public int getCurrentId() {
        return mCurrentId;
    }

    public void setCurrentId(int currentId) {
        mCurrentId = currentId;
    }

    public boolean isUserCheckChoice(int questId, int choiceId) {
        return mUserChecks.get(questId).get(choiceId);
    }

    public BitSet getUserChecksBitset(int questId) {
        return mUserChecks.get(questId);
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

    public void incrementWrong() {
        mWrongAnswerCount++;
    }

    public void incrementRight() {
        mRightAnswerCount++;
    }

    public int getRightCount() {
        return mRightAnswerCount;
    }

    public int getWrongCount() {
        return mWrongAnswerCount;
    }

    public int getTotalLength() {
        return mTotalLength;
    }

    public static void saveSession(Activity activity) {
        try {
            FileOutputStream fos = activity
                    .openFileOutput(SESSION_BACKUP, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(ourInstance);
            os.close();
            fos.close();
            Log.d(TAG, "saveSession: SUCCESS");
        } catch (IOException ioe) {
            Log.d(TAG, "saveSession: FAILED");
            ioe.printStackTrace();
        }
    }

    private static boolean loadSession(Activity activity) {
        try {
            FileInputStream fis = activity.openFileInput(SESSION_BACKUP);
            ObjectInputStream is = new ObjectInputStream(fis);
            ourInstance = (QuestSession)is.readObject();
            is.close();
            fis.close();
            Log.d(TAG, "loadSession: SUCCESS");
            return true;
        } catch (Exception ioe) {
            Log.d(TAG, "loadSession: FAILED");
            return false;
        }
    }

}
