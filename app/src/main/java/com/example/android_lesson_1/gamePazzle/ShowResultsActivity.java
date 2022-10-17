package com.example.android_lesson_1.gamePazzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_lesson_1.R;
import com.example.android_lesson_1.gamePazzle.cache.MemoryHelper;
import com.example.android_lesson_1.gamePazzle.models.UserData;

import java.util.ArrayList;

public class ShowResultsActivity extends AppCompatActivity {
    private LinearLayout rootGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        rootGroup = findViewById(R.id.result_group);

        ArrayList<UserData> list = MemoryHelper.getmHelper().getResultList();


        for(UserData data: list){

        }


        for (int i = 0; i < list.toArray().length; i++) {
            TextView name = new TextView(this);
            TextView step = new TextView(this);
            TextView time = new TextView(this);
            LinearLayout layout = new LinearLayout(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            layout.setOrientation(LinearLayout.VERTICAL);

            name.setText((i+1) +") name: "+list.get(i).getName());
            step.setText("     step: " + list.get(i).getStep());
            time.setText("     time: " + timeFormat(list.get(i).getTime()));
            name.setTextSize(18);
            layout.addView(name);
            layout.addView(step);
            layout.addView(time);

            layout.setPadding(16, 30, 16, 16);

            layout.setLayoutParams(params);
            rootGroup.addView(layout);
        }
    }

    private String timeFormat(int time) {
        int hour = time / 3600;
        int minute = time % 3600 / 60;
        int second = time % 60;

        return  String.format("%02d:%02d:%02d", hour, minute, second);
    }
}