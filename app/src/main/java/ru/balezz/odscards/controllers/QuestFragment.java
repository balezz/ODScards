package ru.balezz.odscards.controllers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.balezz.odscards.R;
import ru.balezz.odscards.models.Quest;
import ru.balezz.odscards.models.QuestLab;

public class QuestFragment extends Fragment {
    private static final String TAG = "QuestFragment";

    private Quest mQuest;

    public static QuestFragment newInstance() {
        return new QuestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuest = QuestLab.getInstance().getQuests().get(0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quest, container, false);
        LinearLayout questLayout = (LinearLayout) v.findViewById(R.id.quest_layout);

        questLayout.addView(getQuestion());
        int choiceCount = mQuest.getChoiceCount();
        Log.i(TAG, "onCreateView: choiceCount = " + choiceCount);
        for (int i = 0; i < choiceCount; i++) {
            questLayout.addView(getChoice(i));
        }
        return v;
    }

    TextView getQuestion() {
        TextView textView = new TextView(getActivity());
        textView.setText(mQuest.getQuestion());
        LayoutParams textViewParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        return textView;
    }

    LinearLayout getChoice(int index) {
        LinearLayout layout = new LinearLayout(getActivity());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        CheckBox checkBox = new CheckBox(getActivity());
        layout.addView(checkBox);
        TextView textAnswer = new TextView(getActivity());
        textAnswer.setText(mQuest.getAnswers()[index]);
        layout.addView(textAnswer);


        return layout;
    }
}
