package ru.balezz.odscards;

import androidx.fragment.app.Fragment;

import ru.balezz.odscards.models.FlashCard;

public class FlashCardActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return FlashCardFragment.newInstance();
    }
}
