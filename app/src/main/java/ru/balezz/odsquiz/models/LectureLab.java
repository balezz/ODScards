package ru.balezz.odsquiz.models;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/** Now it`s just have stub array. todo persist lectures in database*/
public class LectureLab {
    static LectureLab mLectureLab;
    List<Lecture> mLectures;

    public static LectureLab getInstance() {
        if (mLectureLab == null)
            return new LectureLab();
        else
            return mLectureLab;
    }

    private LectureLab() {
        mLectures = new ArrayList<>();
        Lecture lecture;
        lecture = new Lecture("Тема 1. Первичный анализ данных с Pandas.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/322626/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 2. Визуализация данных c Python.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/323210/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 3. Классификация, деревья решений и метод ближайших соседей.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/322534/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 4. Линейные модели классификации и регрессии.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/323890/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 5. Композиции: бэггинг, случайный лес.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/324402/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 6. Построение и отбор признаков.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/325422/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 7. Обучение без учителя: PCA и кластеризация.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/325654/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 8. Обучение на гигабайтах с Vowpal Wabbit.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/326418/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 9. Анализ временных рядов с помощью Python.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/327242/"));
        mLectures.add(lecture);

        lecture = new Lecture("Тема 10. Градиентный бустинг.");
        lecture.setUri(Uri.parse("https://habr.com/en/company/ods/blog/327250/"));
        mLectures.add(lecture);

    }

    public List<Lecture> getLectures() {
        return mLectures;
    }
}
