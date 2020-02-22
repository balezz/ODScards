package ru.balezz.odsquiz.models;

import android.net.Uri;

enum Course{ MLCourse, DLCourse}

public class Lecture {
    private Course mCourse;
    private String mTitle;
    private Uri mUri;
    private String mIconPath;

    Lecture(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri uri) {
        mUri = uri;
    }

    public String getIconPath() {
        return mIconPath;
    }

    public void setIconPath(String iconPath) {
        mIconPath = iconPath;
    }
}
