package ru.balezz.odscards.controllers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

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
    MaterialCardView mCardFace;
    MaterialCardView mCardBack;
    boolean mCardFaceVisible = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_flashcard, container, false);

        final Animation animationRotate = AnimationUtils.
                loadAnimation(getActivity(), R.anim.rotate_center);

        mCardFace = (MaterialCardView) v.findViewById(R.id.card_face);
        mCardBack = (MaterialCardView) v.findViewById(R.id.card_back);
        mCardBack.setVisibility(View.GONE);

        mCardFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mCardFaceVisible = false;
                    applyRotation(mCardFace);
                    mCardFace.setVisibility(View.GONE);
                    mCardBack.setVisibility(View.VISIBLE);
            }
        });

        mCardBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCardFaceVisible = true;
                applyRotation(mCardBack);
                mCardFace.setVisibility(View.VISIBLE);
                mCardBack.setVisibility(View.GONE);
            }
        });

        return v;
    }

    private void applyRotation(MaterialCardView cardView) {
        final float centerX = cardView.getWidth() / 2.0f;
        final float centerY = cardView.getHeight() / 2.0f;

        final Rotate3dAnimation rotation = new Rotate3dAnimation(0, 0,
                0, 180, 0, 0);
        rotation.setDuration(500);
        rotation.setInterpolator(new AccelerateInterpolator());
        cardView.startAnimation(rotation);
    }
}
