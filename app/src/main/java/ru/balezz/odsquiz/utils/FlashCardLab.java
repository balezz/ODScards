package ru.balezz.odsquiz.utils;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.balezz.odsquiz.models.FlashCard;
import ru.balezz.odsquiz.models.Quest;

public class FlashCardLab {
    private static FlashCardLab ourInstance;
    List<FlashCard> mFlashCards;

    public static FlashCardLab getInstance(Activity activity) {
        if (ourInstance == null) {
            ourInstance = new FlashCardLab(activity);
        }
        return ourInstance;
    }

    private FlashCardLab(Activity activity) {
        mFlashCards = new ArrayList<>();
        fetchFlashCards(activity);
    }

    public List<FlashCard> getFlashCards() {
        return mFlashCards;
    }

    private void fetchFlashCards(Activity activity) {
        String jsonString = AssetsLoader
                .loadStringFromAssets(activity, "FlashCards.json");
        parseItems(mFlashCards, jsonString);
    }

    private void parseItems(List<FlashCard> flashCards, String jsonString) {
        try {
            JSONObject jsonBody = new JSONObject(jsonString);
            JSONArray cardsJA = jsonBody.getJSONArray("FlashCards");
            for (int i = 0; i < cardsJA.length(); i++) {
                JSONObject questJO = cardsJA.getJSONObject(i);
                FlashCard fc = parseFlashCard(questJO);
                flashCards.add(fc);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private FlashCard parseFlashCard(JSONObject cardJO) throws JSONException {
        FlashCard flashCard = new FlashCard();
        String face = cardJO.getString("Face");
        String back = cardJO.getString("Back");
        flashCard.setQuestion(face);
        flashCard.setAnswer(back);
        return flashCard;
    }
}
