package ru.balezz.odsquiz.models;

import android.net.Uri;

public class Lecture {
    private String mTitle;
    private Uri mUri;
    private String mIconPath;

    public Lecture(String title) {
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
