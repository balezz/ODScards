package ru.balezz.odscards;

import androidx.fragment.app.Fragment;

import ru.balezz.odscards.controllers.FlashCardFragment;

public class FlashCardActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FlashCardFragment.newInstance();
    }
}
