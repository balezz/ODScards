package ru.balezz.odscards;

import androidx.fragment.app.Fragment;

import ru.balezz.odscards.controllers.QuestFragment;

public class QuestActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return QuestFragment.newInstance();
    }
}
