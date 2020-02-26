package ru.balezz.odsquiz.utils;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.IdentityHashMap;
import java.util.List;

import ru.balezz.odsquiz.models.Quest;

public class QuestsFetchr {
    private static final String TAG = "QuestsFetchr";
    Activity mActivity;
    List<Quest> mQuests;

    public QuestsFetchr(Activity activity) {
        mActivity = activity;
    }

    public List<Quest> fetchItems() {
        if (mQuests == null) {
            mQuests = new ArrayList<>();
            String jsonString = loadStringFromAssets("OdsQuiz.json");
            parseItems(mQuests, jsonString);
        }
        return mQuests;
    }

    private String loadStringFromAssets(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = mActivity.getAssets().open(fileName);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException ioe) {
            Log.e(TAG, "loadQuestsFromAssets: ", ioe);
        }
        return sb.toString();
    }

    private void parseItems(List<Quest> questsList, String jsonString) {

        try {
            JSONObject jsonBody = new JSONObject(jsonString);
            JSONArray questsJA = jsonBody.getJSONArray("Questions");
            for (int i = 0; i < questsJA.length(); i++) {
                JSONObject questJO = questsJA.getJSONObject(i);
                Quest quest = parseQuest(questJO);
                questsList.add(quest);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private Quest parseQuest(JSONObject questJO) throws JSONException {
            Quest quest = new Quest();
            int id = Integer.parseInt(questJO.getString("Id"));
            String question = questJO.getString("Question");
            String explanation = questJO.getString("Explanation");
            JSONArray choicesJA = questJO.getJSONArray("Choices");
            BitSet bitSet = new BitSet();
            List<String> choicesList = new ArrayList<>();
            for (int i = 0; i < choicesJA.length(); i++) {
                JSONObject choiceJO = choicesJA.getJSONObject(i);
                String choice = choiceJO.getString("Choice");
                Integer right = choiceJO.getInt("Right");
                choicesList.add(choice);
                if (right == 1) bitSet.set(i, true);
            }

            quest.setId(id);
            quest.setQuestion(question);
            quest.setExplanation(explanation);
            quest.setChoices(choicesList);
            quest.setRightAnswers(bitSet);


            Log.d(TAG, "parseQuest: Question" + question);
            Log.d(TAG, "parseQuest: BitSet:" + bitSet.toString());
            Log.d(TAG, "parseQuest: ChoicesList:" + choicesList.toString());
            return quest;
    }
}
