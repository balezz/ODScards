package ru.balezz.odscards;

import androidx.fragment.app.Fragment;

import ru.balezz.odscards.controllers.LectureListFragment;

public class LectureListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LectureListFragment.newInstance();
    }
}
