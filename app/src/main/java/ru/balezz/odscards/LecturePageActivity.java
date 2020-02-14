package ru.balezz.odscards;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.fragment.app.Fragment;

public class LecturePageActivity extends SingleFragmentActivity {

    private static final String TAG = "LecturePageActivity";

    public static Intent newIntent(Context context, Uri lecturePageUri) {
        Log.d(TAG, "newIntent: invoke");
        Intent i = new Intent(context, LecturePageActivity.class);
        i.setData(lecturePageUri);
        return i;
    }

    @Override
    protected Fragment createFragment() {
        return LecturePageFragment.newInstance(getIntent().getData());
    }
}
