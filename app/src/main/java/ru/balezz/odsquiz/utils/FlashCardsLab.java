package ru.balezz.odsquiz.utils;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.balezz.odsquiz.R;
import ru.balezz.odsquiz.models.FlashCard;

public class FlashCardsLab {
    private static final String TAG = "FlashCardsLab";
    private static final String ASSETS_FILE_NAME = "FlashCards.json";
    private static FlashCardsLab ourInstance;
    private final Activity mActivity;
    private List<FlashCard> mFlashCards;

    public static FlashCardsLab getInstance(Activity activity) {
        if (ourInstance == null) {
            ourInstance = new FlashCardsLab(activity);
        }
        return ourInstance;
    }

    private FlashCardsLab(Activity activity) {
        mFlashCards = new ArrayList<>();
        mActivity = activity;
        fetchFromAssets();
    }

    public List<FlashCard> getFlashCards() {
        return mFlashCards;
    }

    public void fetchFromUri(Uri uri) {
        String jsonString = null;
        try {
            InputStream inputStream = mActivity.getContentResolver().openInputStream(uri);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            inputStream.close();
            bufferedReader.close();
            jsonString = sb.toString();
        } catch (IOException ioe) {
            Log.e(TAG, "fetchFromUri: cant read file: " + uri);
        }
        mFlashCards.clear();
        parseItems(mFlashCards, jsonString);
    }

    private void fetchFromAssets() {
        String jsonString = AssetsLoader
                .loadStringFromAssets(mActivity, ASSETS_FILE_NAME);
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
            Toast.makeText(mActivity, R.string.cant_parse_json, Toast.LENGTH_LONG).show();
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
