package ru.balezz.odscards.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

import ru.balezz.odscards.R;
import ru.balezz.odscards.utils.Rotate3dAnimation;

public class FlashCardFragment extends Fragment {
    public static FlashCardFragment newInstance() {
        return new FlashCardFragment();
    }
    MaterialCardView mCardView;
    TextView mCardText;
    boolean mCardFaceVisible = true;


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

        return v;
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
                    mCardText.setText(R.string.quest_example);
                else
                    mCardText.setText(R.string.answer_example);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

        });
        rotation.setInterpolator(new AccelerateInterpolator());
        mCardView.startAnimation(rotation);
    }
}
