package ru.balezz.odscards;

import androidx.fragment.app.Fragment;

import ru.balezz.odscards.controllers.MainFragment;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MainFragment.getInstance();
    }
}
