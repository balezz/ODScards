package ru.balezz.odsquiz.controllers;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import android.widget.ProgressBar;
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
import ru.balezz.odsquiz.utils.QuestsFetchr;

public class QuestFragment extends Fragment {
    private static final String TAG = "QuestFragment";

    private ImageButton mForwardButton;
    private ImageButton mBackwardButton;
    private LinearLayout mQuestLayout;
    private TextView mQuestCounterView;
    private TextView mRightCountView;
    private TextView mWrongCountView;
    private ProgressBar mProgressBar;

    private List<Quest> mQuests;
    private QuestSession mQuestSession;
    private Quest mQuest;
    private int mQuestId;

    public static QuestFragment newInstance() {
        return new QuestFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuests = new QuestsFetchr(getActivity()).fetchItems();
        QuestLab.getInstance().setQuests(mQuests);
        mQuestSession = QuestSession.getInstance(mQuests);
        mQuestId = mQuestSession.getCurrentId();
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
        mQuestCounterView = (TextView) v.findViewById(R.id.text_quest_count);
        mRightCountView = (TextView) v.findViewById(R.id.right_count);
        mWrongCountView = (TextView) v.findViewById(R.id.wrong_count);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        mProgressBar.setMax(mQuests.size() - 1);

        mForwardButton.setOnClickListener(v1 -> {
            if (!mQuestSession.isQuestAnswered(mQuestId)){
                mQuestLayout.addView(getExplanationView());
                mQuestSession.setQuestIsAnswered(mQuestId);
                updateStatistic(mQuestSession.checkAnswerIsRight(mQuestId));
                return;
            }
            if (mQuestId < mQuests.size() - 1) {
                mQuestSession.setCurrentId(++mQuestId);
                mQuest = mQuests.get(mQuestId);
                updateUI();
            }
        });
        mBackwardButton.setOnClickListener(v2 -> {
            if (mQuestId > 0) {
                mQuestSession.setCurrentId(--mQuestId);
                mQuest = mQuests.get(mQuestId);
                updateUI();
            }
        });
        updateUI();

        return v;
    }

    private void updateStatistic(boolean checkAnswerIsRight) {
        if (checkAnswerIsRight) {
            mQuestSession.incrementRight();
        } else {
            mQuestSession.incrementWrong();
        }
        updateStatisticViews();
    }

    private void updateUI() {
        mProgressBar.setProgress(mQuestId);
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
        TextView textView = new TextView(
                new ContextThemeWrapper(getActivity(), R.style.QuestText),
                null, 0);
        textView.setText(mQuest.getQuestion());
        LayoutParams textViewParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textView.setPadding(0, 0, 0, 16);
        textView.setLayoutParams(textViewParams);
        return textView;
    }

    private TextView getExplanationView() {
        TextView textView = new TextView(getActivity());
        textView.setText(mQuest.getExplanation());
        LayoutParams textViewParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        textViewParams.setMargins(0, 8, 0, 0);
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
            radioButton.setTextSize(20);
            radioButton.setPadding(0, 8, 0, 8);
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
        choiceLayout.setPadding(0, 8, 0, 8);
        final CheckBox checkBox = new CheckBox(getActivity());
        checkBox.setChecked(mQuestSession.getUserCheck(mQuestId, index));
        checkBox.setOnCheckedChangeListener(
                (buttonView, isChecked) -> mQuestSession.toggleUserCheck(mQuestId, index));
        choiceLayout.addView(checkBox);
        TextView textAnswer = new TextView(
                new ContextThemeWrapper(getActivity(), R.style.QuestText),
                null, 0);
        textAnswer.setText(mQuest.getChoices().get(index));
        choiceLayout.addView(textAnswer);
        choiceLayout.setOnClickListener(v -> checkBox.toggle());
        return choiceLayout;
    }

    private void updateStatisticViews() {
        String questCountString = getString(R.string.quest_counter, mQuestId+1);
        mQuestCounterView.setText(questCountString);
        mRightCountView.setText(mQuestSession.getRightCount());
        mWrongCountView.setText(mQuestSession.getWrongCount());

    }
}
