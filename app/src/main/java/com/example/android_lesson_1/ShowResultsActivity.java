package com.example.android_lesson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android_lesson_1.cache.MemoryHelper;
import com.example.android_lesson_1.models.UserData;

import org.w3c.dom.Text;

public class ShowResultsActivity extends AppCompatActivity {
    private LinearLayout rootGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_results);

        rootGroup = findViewById(R.id.result_group);

        UserData userData = MemoryHelper.getmHelper().getData();


        for (int i = 0; i < 18; i++) {
            TextView name = new TextView(this);
            TextView step = new TextView(this);
            TextView time = new TextView(this);
            LinearLayout layout = new LinearLayout(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            layout.setOrientation(LinearLayout.VERTICAL);

            name.setText((i+1) +") name: "+userData.getName());
            step.setText("     step: " + userData.getStep());
            time.setText("     time: " + userData.getTime());
            layout.addView(name);
            layout.addView(step);
            layout.addView(time);

            layout.setPadding(16, 30, 16, 16);

            layout.setLayoutParams(params);
            rootGroup.addView(layout);
        }
    }
}