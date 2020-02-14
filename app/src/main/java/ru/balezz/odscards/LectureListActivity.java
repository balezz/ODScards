package ru.balezz.odscards;

import android.content.Intent;

import androidx.fragment.app.Fragment;

public class LectureListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LectureListFragment.newInstance();
    }
}
