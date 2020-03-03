package ru.balezz.odsquiz.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import ru.balezz.odsquiz.R;
import ru.balezz.odsquiz.models.FlashCard;
import ru.balezz.odsquiz.utils.FlashCardsLab;
import ru.balezz.odsquiz.utils.Rotate3dAnimation;

public class FlashCardFragment extends Fragment {
    private static final String TAG = "FlashCardFragment";
    private static final int READ_REQUEST_CODE = 21;
    MaterialCardView mCardView;
    TextView mCardText;
    ImageButton mForwardButton;
    ImageButton mBackwardButton;
    Switch mSwitchKnown;
    ProgressBar mProgressBar;

    List<FlashCard> mFlashCards;
    FlashCard mFlashCard;
    int mFlashId = 0;
    boolean mCardFaceVisible = true;

    public static FlashCardFragment newInstance() {
        return new FlashCardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFlashCards = FlashCardsLab.getInstance(getActivity()).getFlashCards();
        mFlashCard = mFlashCards.get(mFlashId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_flashcard, container, false);

        Toolbar toolbar = v.findViewById(R.id.app_toolbar);
        Log.d(TAG, "onCreateView: " + toolbar);
        toolbar.inflateMenu(R.menu.flashcard_menu);
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.download_flashcard) {
                downloadFlashCard();
            }
            return true;
        } );

        mCardView = (MaterialCardView) v.findViewById(R.id.card_face);
        mCardText = (TextView) v.findViewById(R.id.card_text);
        mCardView.setOnClickListener(v1 -> {
                mCardFaceVisible = !mCardFaceVisible;
                applyRotation();
        });
        mCardText.setText(mFlashCard.getQuestion());

        mForwardButton = (ImageButton) v.findViewById(R.id.btn_forward);
        mBackwardButton = (ImageButton) v.findViewById(R.id.btn_back);
        mForwardButton.setOnClickListener(v2 -> {
            if (mFlashId < mFlashCards.size() - 1) {
                mFlashCard = mFlashCards.get(++mFlashId);
                updateUI();
            }
        });
        mBackwardButton.setOnClickListener(v3 -> {
            if (mFlashId > 0) {
                mFlashCard = mFlashCards.get(--mFlashId);
                updateUI();
            }
        });

        mSwitchKnown = (Switch) v.findViewById(R.id.switchKnown);
        mSwitchKnown.setChecked(mFlashCard.isKnown());
        mSwitchKnown.setOnClickListener(v4 ->
                mFlashCard.setKnown(mSwitchKnown.isChecked()));

        mProgressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        mProgressBar.setMax(mFlashCards.size() - 1);
        mProgressBar.setProgress(mFlashId);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == READ_REQUEST_CODE) {
            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                FlashCardsLab.getInstance(getActivity()).fetchFromUri(uri);
            }
        }
    }

    private void downloadFlashCard() {
        Log.d(TAG, "downloadFlashCard: ");
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    private void updateUI() {
        mCardFaceVisible = true;
        mCardText.setText(mFlashCard.getQuestion());
        mSwitchKnown.setChecked(mFlashCard.isKnown());
        mProgressBar.setProgress(mFlashId);
    }

    private void applyRotation(){
        final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 0,
                0, 180, 0, 0);
        rotation.setDuration(500);
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (mCardFaceVisible)
                    mCardText.setText(mFlashCard.getQuestion());
                else
                    mCardText.setText(mFlashCard.getAnswer());
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });
        rotation.setInterpolator(new AccelerateInterpolator());
        mCardView.startAnimation(rotation);
    }
}
