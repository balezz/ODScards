package ru.balezz.odscards.models;

import android.net.Uri;

enum Course{ MLCourse, DLCourse}

public class Lecture {
    private Course mCourse;
    private String mTitle;
    private Uri mUri;
    private String mBody;

    Lecture(String title) {
        mTitle = title;
        mUri = Uri.parse("https://habr.com/ru/company/ods/blog/322626/");
        mBody = "Это заглушка для текста лекции.";
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
}
