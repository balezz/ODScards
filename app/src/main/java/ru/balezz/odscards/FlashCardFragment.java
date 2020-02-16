package ru.balezz.odscards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

public class FlashCardFragment extends Fragment {
    public static FlashCardFragment newInstance() {
        return new FlashCardFragment();
    }
    MaterialCardView mMaterialCardView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_flashcard, container, false);

        final Animation animationRotate = AnimationUtils.
                loadAnimation(getActivity(), R.anim.rotate_center);

        mMaterialCardView = (MaterialCardView) v.findViewById(R.id.mcview);
        mMaterialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMaterialCardView.startAnimation(animationRotate);
            }
        });

        return v;
    }
}
