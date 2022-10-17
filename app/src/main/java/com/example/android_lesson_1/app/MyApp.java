package com.example.android_lesson_1.app;

import android.app.Application;

import com.example.android_lesson_1.gamePazzle.cache.MemoryHelper;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MemoryHelper.init(this);
    }
}
