package ru.balezz.odsquiz.controllers;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import ru.balezz.odsquiz.LecturePageActivity;
import ru.balezz.odsquiz.R;
import ru.balezz.odsquiz.models.Lecture;
import ru.balezz.odsquiz.utils.LectureLab;


public class LectureListFragment extends Fragment {

    private static final String TAG = "LectureListFragment";
    private RecyclerView mRecyclerView;
    private LectureAdapter mAdapter;

    public class LectureHolder extends RecyclerView.ViewHolder
                                    implements View.OnClickListener {
        TextView mTitle;
        Lecture mLecture;
        ImageView mIconView;

        public LectureHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.lecture_list_item, parent, false));
            itemView.setOnClickListener(this);
            mTitle = itemView.findViewById(R.id.lecture_item_title);
            mIconView = itemView.findViewById(R.id.lectureIcon);
        }

        public void bind(Lecture lecture) {
            mLecture = lecture;
            mTitle.setText(lecture.getTitle());
            Drawable d = loadFromAsset(lecture.getIconPath());
            if (d != null) {
                mIconView.setImageDrawable(d);
            }
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + mLecture.getTitle());
            Intent i;
            if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 22) {
                i = new Intent(Intent.ACTION_VIEW, mLecture.getUri());
            } else {
                i = LecturePageActivity.newIntent(getActivity(), mLecture.getUri());
            }
            startActivity(i);
        }
    }

    public class LectureAdapter extends RecyclerView.Adapter<LectureHolder> {
        private List<Lecture> mLectures;

        public LectureAdapter(List<Lecture> lectures) {
            mLectures = lectures;
        }

        @NonNull
        @Override
        public LectureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new LectureHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull LectureHolder holder, int position) {
            Lecture lecture = mLectures.get(position);
            holder.bind(lecture);
        }

        @Override
        public int getItemCount() {
            return mLectures.size();
        }

        public void setLectures(List<Lecture> lectures) {
            mLectures = lectures;
        }
    }

    public static LectureListFragment newInstance() {
        return new LectureListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lecture_list, container, false);
        mRecyclerView = v.findViewById(R.id.lectures_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return v;
    }

    private void updateUI() {
        LectureLab lectureLab = LectureLab.getInstance();
        List<Lecture> lectures = lectureLab.getLectures();
        if (mAdapter == null) {
            mAdapter = new LectureAdapter(lectures);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setLectures(lectures);
            mAdapter.notifyDataSetChanged();
        }
    }

    private Drawable loadFromAsset(String fileName) {
        try {
            InputStream is = getActivity().getAssets().open("ml_icons/" + fileName);
            Drawable d = Drawable.createFromStream(is, null);
            return d;
        } catch (IOException ioe) {
            Log.d(TAG, "loadFromAsset: failed");
            return null;
        }

    }
}
