package ru.balezz.odsquiz.controllers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import ru.balezz.odsquiz.R;
import ru.balezz.odsquiz.utils.QuestSession;

public class MainFragment extends Fragment implements FragmentManager.OnBackStackChangedListener {
    public static final String TAG = "MainFragment";
    QuestSession mQuestSession;
    FragmentManager mFragManager;
    private int total, right, wrong;
    TextView mTextTotal;
    TextView mTextRight;
    TextView mTextWrong;
    ImageView mLectureImageView;
    ImageView mFlashCardImageView;
    ImageView mQuestImageView;
    ImageView mExamImageView;
    TextView mIntstructionTextView;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        Toolbar toolbar = v.findViewById(R.id.app_toolbar);
        Log.d(TAG, "onCreateView: " + toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.clear_session) {
                QuestSession.clearSession(getActivity());
                updateStatistics();
            }
            return true;
        } );

        mTextTotal = v.findViewById(R.id.text_total);
        mTextRight = v.findViewById(R.id.text_right);
        mTextWrong = v.findViewById(R.id.text_wrong);
        updateStatistics();

        mFragManager = getActivity().getSupportFragmentManager();
        mLectureImageView = (ImageView) v.findViewById(R.id.iv_lectures);
        mLectureImageView.setOnClickListener(v1 ->
                transactFragment(LectureListFragment.newInstance())
        );

        mFlashCardImageView = (ImageView) v.findViewById(R.id.iv_flashcards);
        mFlashCardImageView.setOnClickListener(v2 ->
                transactFragment(FlashCardFragment.newInstance()));

        mQuestImageView = (ImageView) v.findViewById(R.id.iv_quest);
        mQuestImageView.setOnClickListener(v3 ->
                transactFragment(QuestFragment.newInstance()));

        mExamImageView = (ImageView) v.findViewById(R.id.iv_exam);
        mExamImageView.setOnClickListener(v4 -> {
            Toast.makeText(getContext(), R.string.exam_not_availible,
                    Toast.LENGTH_LONG).show();
        });

        mIntstructionTextView = (TextView) v.findViewById(R.id.instruction_text);
        mIntstructionTextView.setOnClickListener(v1 ->
                transactFragment(InstructionFragment.newInstance()));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateStatistics();
    }

    @Override
    public void onBackStackChanged() {

    }

    private void updateStatistics() {
        mQuestSession = QuestSession.getInstance(getActivity());
        right = mQuestSession.getRightCount();
        wrong = mQuestSession.getWrongCount();
        total = mQuestSession.getTotalLength();
        mTextTotal.setText(getString(R.string.total, total));
        mTextRight.setText(getString(R.string.right, right));
        mTextWrong.setText(getString(R.string.wrong, wrong));

    }

    private void transactFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        mFragManager.addOnBackStackChangedListener(this);
        fragmentTransaction.replace(R.id.fragment_container,
                fragment, "h");
        fragmentTransaction.addToBackStack("h");
        fragmentTransaction.commit();
    }
}
