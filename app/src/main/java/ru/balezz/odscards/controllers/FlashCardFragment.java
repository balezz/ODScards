package ru.balezz.odscards.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import ru.balezz.odscards.R;
import ru.balezz.odscards.models.FlashCard;
import ru.balezz.odscards.models.FlashCardLab;
import ru.balezz.odscards.utils.Rotate3dAnimation;

public class FlashCardFragment extends Fragment {
    public static FlashCardFragment newInstance() {
        return new FlashCardFragment();
    }
    MaterialCardView mCardView;
    TextView mCardText;
    ImageButton mForwardButton;
    ImageButton mBackwardButton;

    List<FlashCard> mFlashCards;
    FlashCard mFlashCard;
    int mFlashId = 0;
    boolean mCardFaceVisible = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFlashCards = FlashCardLab.getInstance().getFlashCards();
        mFlashCard = mFlashCards.get(mFlashId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_flashcard, container, false);

        mCardView = (MaterialCardView) v.findViewById(R.id.card_face);
        mCardText = (TextView) v.findViewById(R.id.card_text);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mCardFaceVisible = !mCardFaceVisible;
                    applyRotation();
            }
        });

        mForwardButton = (ImageButton) v.findViewById(R.id.btn_forward);
        mBackwardButton = (ImageButton) v.findViewById(R.id.btn_back);
        mForwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlashId < mFlashCards.size() - 1) {
                    mFlashCard = mFlashCards.get(++mFlashId);
                    updateUI();
                }
            }
        });
        mBackwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlashId > 0) {
                    mFlashCard = mFlashCards.get(--mFlashId);
                    updateUI();
                }
            }
        });


        return v;
    }

    private void updateUI() {
        mCardFaceVisible = true;
        mCardText.setText(mFlashCard.getQuestion());
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
