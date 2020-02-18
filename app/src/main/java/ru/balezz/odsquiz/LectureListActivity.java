package ru.balezz.odsquiz;

import androidx.fragment.app.Fragment;

import ru.balezz.odsquiz.controllers.LectureListFragment;

public class LectureListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return LectureListFragment.newInstance();
    }
}
