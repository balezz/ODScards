package ru.balezz.odsquiz.controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.balezz.odsquiz.FlashCardActivity;
import ru.balezz.odsquiz.LectureListActivity;
import ru.balezz.odsquiz.QuestActivity;
import ru.balezz.odsquiz.R;
import ru.balezz.odsquiz.models.QuestSession;

public class MainFragment extends Fragment {
    public static final String TAG = "MainFragment";
    QuestSession mQuestSession;
    private int total, right, wrong;
    TextView mTextTotal;
    TextView mTextRight;
    TextView mTextWrong;
    ImageView mLectureImageView;
    ImageView mFlashCardImageView;
    ImageView mQuestImageView;

    public static MainFragment getInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mQuestSession = QuestSession.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mTextTotal = v.findViewById(R.id.text_total);
        mTextRight = v.findViewById(R.id.text_right);
        mTextWrong = v.findViewById(R.id.text_wrong);
        updateStatistics();

        mLectureImageView = (ImageView) v.findViewById(R.id.iv_lectures);
        mLectureImageView.setOnClickListener(v1 -> {
            Intent i = new Intent(getActivity(), LectureListActivity.class);
            startActivity(i);
        });

        mFlashCardImageView = (ImageView) v.findViewById(R.id.iv_flashcards);
        mFlashCardImageView.setOnClickListener(v2 -> {
            Intent i = new Intent(getActivity(), FlashCardActivity.class);
            startActivity(i);
        });

        mQuestImageView = (ImageView) v.findViewById(R.id.iv_quest);
        mQuestImageView.setOnClickListener(v3 -> {
            Intent i = new Intent(getActivity(), QuestActivity.class);
            startActivity(i);
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateStatistics();
    }

    private void updateStatistics() {
        right = mQuestSession.getRightCount();
        wrong = mQuestSession.getWrongCount();
        total = mQuestSession.getTotalLength();
        mTextTotal.setText(getString(R.string.total, total));
        mTextRight.setText(getString(R.string.right, right));
        mTextWrong.setText(getString(R.string.wrong, wrong));

    }
}
