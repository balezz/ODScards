package ru.balezz.odsquiz.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import ru.balezz.odsquiz.R;
import ru.balezz.odsquiz.models.AnswerType;
import ru.balezz.odsquiz.models.Quest;
import ru.balezz.odsquiz.models.QuestLab;
import ru.balezz.odsquiz.models.QuestSession;

public class QuestFragment extends Fragment {
    private static final String TAG = "QuestFragment";

    private ImageButton mForwardButton;
    private ImageButton mBackwardButton;
    private LinearLayout mQuestLayout;
    private TextView mQuestCounterView;
    private TextView mQuestStasticView;
    private List<Quest> mQuests;
    private QuestSession mQuestSession;
    private Quest mQuest;
    private int mQuestId;
    private int mQuestAnsweredRightCount;
    private int mQuestAnsweredWrongCount;

    public static QuestFragment newInstance() {
        return new QuestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuests = QuestLab.getInstance().getQuests();
        mQuestId = 0;
        mQuest = mQuests.get(mQuestId);
        mQuestSession = QuestSession.getInstance(mQuests);
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
        mQuestCounterView = (TextView) v.findViewById(R.id.text_quest_count);
        mQuestStasticView = (TextView) v.findViewById(R.id.text_statistic);

        mForwardButton.setOnClickListener(v1 -> {
            if (!mQuestSession.isQuestAnswered(mQuestId)){
                mQuestLayout.addView(getExplanationView());
                mQuestSession.setQuestIsAnswered(mQuestId);
                updateStatistic(mQuestSession.checkAnswerIsRight(mQuestId));
                return;
            }
            if (mQuestId < mQuests.size() - 1) {
                mQuest = mQuests.get(++mQuestId);
                updateUI();
            }
        });
        mBackwardButton.setOnClickListener(v12 -> {
            if (mQuestId > 0) {
                mQuest = mQuests.get(--mQuestId);
                updateUI();
            }
        });
        updateUI();

        return v;
    }

    private void updateStatistic(boolean checkAnswerIsRight) {
        if (checkAnswerIsRight) {
            mQuestAnsweredRightCount++;
        } else {
            mQuestAnsweredWrongCount++;
        }
        updateStatisticViews();
    }

    private void updateUI() {
        updateStatisticViews();
        mQuestLayout.removeAllViews();
        mQuestLayout.addView(getQuestionView());
        if (mQuest.getType() == AnswerType.Check) {
            for (int i = 0; i < mQuest.getChoiceCount(); i++) {
                mQuestLayout.addView(getCheckView(i));
            }
        } else {
            mQuestLayout.addView(getRadioView());
        }
        if (mQuestSession.isQuestAnswered(mQuestId)){
            mQuestLayout.addView(getExplanationView());
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

    private TextView getExplanationView() {
        TextView textView = new TextView(getActivity());
        textView.setText(mQuest.getExplanation());
        LayoutParams textViewParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(textViewParams);
        return textView;
    }

    /** Generate RadioGroup programmatically from mQuest.
     *  We need to know witch item was selected to set user`s answer,
     *  so for-loop with explicit index preferred here. */
    private View getRadioView() {
        RadioGroup radioGroup = new RadioGroup(getActivity());
        for (int i = 0; i < mQuest.getChoiceCount(); i++) {
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(mQuest.getChoices().get(i));
            radioGroup.addView(radioButton);
            final int index = i;
            radioButton.setOnClickListener(
                    v -> mQuestSession.setSingleUserCheck(mQuestId, index));
        }
        return radioGroup;
    }

    /** Generate one line horizontal LinearLayout
     *  with CheckBox and question text */
    private LinearLayout getCheckView(final int index) {
        LinearLayout choiceLayout = new LinearLayout(getActivity());
        choiceLayout.setOrientation(LinearLayout.HORIZONTAL);
        final CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setChecked(mQuestSession.getUserCheck(mQuestId, index));
        checkBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> mQuestSession.toggleUserCheck(mQuestId, index));
        choiceLayout.addView(checkBox);
        TextView textAnswer = new TextView(getActivity());
        textAnswer.setText(mQuest.getChoices().get(index));
        choiceLayout.addView(textAnswer);
        choiceLayout.setOnClickListener(v -> checkBox.toggle());
        return choiceLayout;
    }

    private void updateStatisticViews() {
        String questCountString = getString(R.string.quest_counter, mQuestId+1);
        mQuestCounterView.setText(questCountString);
        String questStatisticString = getString(
                R.string.quest_statistic, mQuestAnsweredRightCount, mQuestAnsweredWrongCount);
        mQuestStasticView.setText(questStatisticString);
    }
}
