package com.example.android_lesson_1.gamePazzle.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.android_lesson_1.gamePazzle.models.UserData;

import java.util.ArrayList;

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
        mSharedPreferences.edit().putString("name_"+getLastIndex(), userData.getName()).apply();
        mSharedPreferences.edit().putInt("step_"+getLastIndex(), userData.getStep()).apply();
        mSharedPreferences.edit().putInt("time_"+getLastIndex(), userData.getTime()).apply();
        saveLastIndex();
    }

    public UserData getData(int index){

        return new UserData(
          mSharedPreferences.getString("name_"+index, ""),
          mSharedPreferences.getInt("step_"+index, 0),
          mSharedPreferences.getInt("time_"+index, 0)
        );
    }

    private void saveLastIndex(){
        mSharedPreferences.edit().putInt("index", getLastIndex()+1).apply();
    }

    private int getLastIndex(){
        return  mSharedPreferences.getInt("index", 0);
    }

    public ArrayList<UserData> getResultList(){
        ArrayList<UserData> list = new ArrayList<>();

        int n = getLastIndex();
        for (int i = 0; i < n; i++) {
            list.add(getData(i));
        }

        return list;

    }


}
