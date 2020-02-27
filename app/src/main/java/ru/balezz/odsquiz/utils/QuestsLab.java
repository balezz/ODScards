package ru.balezz.odsquiz.utils;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import ru.balezz.odsquiz.models.Quest;

public class QuestsLab implements AssetsLoader {
    private static final String TAG = "QuestsLab";
    private static QuestsLab ourInstance;
    List<Quest> mQuests;

    public static QuestsLab getInstance(Activity activity) {
        if (ourInstance == null) {
            ourInstance = new QuestsLab(activity);
        }
        return ourInstance;
    }

    private QuestsLab(Activity activity) {
        mQuests = fetchItems(activity);
    }

    public List<Quest> getQuests() {
        return mQuests;
    }

    private List<Quest> fetchItems(Activity activity) {
        if (mQuests == null) {
            mQuests = new ArrayList<>();
            String jsonString = AssetsLoader
                    .loadStringFromAssets(activity, "OdsQuiz.json");
            parseItems(mQuests, jsonString);
        }
        return mQuests;
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
        List<String> choicesList = new ArrayList<>();
        BitSet bitSet = new BitSet();
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
        return quest;
    }
}
