package ru.balezz.odscards.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.balezz.odscards.R;
import ru.balezz.odscards.models.Quest;
import ru.balezz.odscards.models.QuestLab;

public class QuestFragment extends Fragment {
    private static final String TAG = "QuestFragment";

    private ImageButton mForwardButton;
    private ImageButton mBackwardButton;
    private LinearLayout mQuestLayout;
    private List<Quest> mQuests;
    private Quest mQuest;
    private int mQuestId;

    public static QuestFragment newInstance() {
        return new QuestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuests = QuestLab.getInstance().getQuests();
        mQuestId = 0;
        mQuest = mQuests.get(mQuestId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_quest, container, false);
        mQuestLayout = (LinearLayout) v.findViewById(R.id.quest_layout);
        mForwardButton = (ImageButton) v.findViewById(R.id.btn_forward);
        mBackwardButton = (ImageButton) v.findViewById(R.id.btn_back);

        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestId < mQuests.size() - 1) {
                    mQuest = mQuests.get(++mQuestId);
                    updateUI();
                }
            }
        });
        mBackwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQuestId > 0) {
                    mQuest = mQuests.get(--mQuestId);
                    updateUI();
                }
            }
        });
        updateUI();

        return v;
    }

    private void updateUI() {
        mQuestLayout.removeAllViews();
        mQuestLayout.addView(getQuestionView());
        for (String choice : mQuest.getChoices()) {
            mQuestLayout.addView(getChoiceView(choice));
        }
    }

    private TextView getQuestionView() {
        TextView textView = new TextView(getActivity());
        textView.setText(mQuest.getQuestion());
        LayoutParams textViewParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        return textView;
    }

    private LinearLayout getChoiceView(String choice) {
        LinearLayout choiceLayout = new LinearLayout(getActivity());
        choiceLayout.setOrientation(LinearLayout.HORIZONTAL);
        CheckBox checkBox = new CheckBox(getActivity());
        choiceLayout.addView(checkBox);
        TextView textAnswer = new TextView(getActivity());
        textAnswer.setText(choice);
        choiceLayout.addView(textAnswer);
        return choiceLayout;
    }
}
