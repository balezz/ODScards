package ru.balezz.odsquiz;

import androidx.fragment.app.Fragment;

import ru.balezz.odsquiz.controllers.QuestFragment;

public class QuestActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return QuestFragment.newInstance();
    }
}
