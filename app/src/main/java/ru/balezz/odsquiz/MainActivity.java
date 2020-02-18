package ru.balezz.odsquiz;

import androidx.fragment.app.Fragment;

import ru.balezz.odsquiz.controllers.MainFragment;


public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return MainFragment.getInstance();
    }
}
