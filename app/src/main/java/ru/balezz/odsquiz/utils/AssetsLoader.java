package ru.balezz.odsquiz.utils;

import android.app.Activity;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public interface AssetsLoader {
    String TAG = "ASSETS_LOADER";
    static String loadStringFromAssets(Activity activity, String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = activity.getAssets().open(fileName);
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
}
