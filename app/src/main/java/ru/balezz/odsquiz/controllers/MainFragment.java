package ru.balezz.odsquiz.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.balezz.odsquiz.FlashCardActivity;
import ru.balezz.odsquiz.LectureListActivity;
import ru.balezz.odsquiz.QuestActivity;
import ru.balezz.odsquiz.R;

public class MainFragment extends Fragment {
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);



        mLectureImageView = (ImageView) v.findViewById(R.id.iv_lectures);
        mLectureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LectureListActivity.class);
                startActivity(i);
            }
        });

        mFlashCardImageView = (ImageView) v.findViewById(R.id.iv_flashcards);
        mFlashCardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FlashCardActivity.class);
                startActivity(i);
            }
        });

        mQuestImageView = (ImageView) v.findViewById(R.id.iv_quest);
        mQuestImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QuestActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

}
