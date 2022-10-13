package com.example.android_lesson_1.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android_lesson_1.models.UserData;

public class MemoryHelper {
    private static MemoryHelper mHelper;
    private SharedPreferences mSharedPreferences;

    private MemoryHelper(Context context){
        mSharedPreferences = context.getSharedPreferences("puzzle15", Context.MODE_PRIVATE);
    }

    public static MemoryHelper getmHelper() {
        return mHelper;
    }

    public static void init(Context context){
        if(mHelper == null) {
            mHelper = new MemoryHelper(context);
        }
    }

    public void saveData(UserData userData){
        mSharedPreferences.edit().putString("name", userData.getName()).apply();
        mSharedPreferences.edit().putInt("step", userData.getStep()).apply();
        mSharedPreferences.edit().putInt("time", userData.getTime()).apply();
    }

    public UserData getData(){

        return new UserData(
          mSharedPreferences.getString("name", ""),
          mSharedPreferences.getInt("step", 0),
          mSharedPreferences.getInt("time", 0)
        );
    }


}
