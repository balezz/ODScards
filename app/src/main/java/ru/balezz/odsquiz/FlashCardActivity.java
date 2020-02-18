package ru.balezz.odsquiz;

import androidx.fragment.app.Fragment;

import ru.balezz.odsquiz.controllers.FlashCardFragment;

public class FlashCardActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FlashCardFragment.newInstance();
    }
}
