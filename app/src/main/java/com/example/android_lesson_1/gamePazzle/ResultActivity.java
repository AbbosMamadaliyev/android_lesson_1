package com.example.android_lesson_1.gamePazzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_lesson_1.R;
import com.example.android_lesson_1.gamePazzle.cache.MemoryHelper;
import com.example.android_lesson_1.gamePazzle.models.UserData;

public class ResultActivity extends AppCompatActivity {

    private Button backToHomeBtn;
    private Button saveDataBtn;
    private TextView resTime;
    private TextView resStep;
    private EditText mEditText;

    private String name;
    private int step;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        step = intent.getIntExtra("step", 0);
        time = intent.getIntExtra("time", 0);

        System.out.println("timem: " + time);

        backToHomeBtn = findViewById(R.id.result_home_btn);
        saveDataBtn = findViewById(R.id.save_result);
        resStep = findViewById(R.id.result_step);
        resTime = findViewById(R.id.result_time);
        mEditText = findViewById(R.id.edit_name);


        resStep.setText(String.format("Your step: %d", step));
        resTime.setText("Your time: " + timeFormat(time));

        setClickListener(step, time);
    }


    private String timeFormat(int time) {
        int hour = time / 3600;
        int minute = time % 3600 / 60;
        int second = time % 60;

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    private void setClickListener(int step, int time) {

        /// navigate to home
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserData usd = MemoryHelper.getmHelper().getData();
//                System.out.println("data1: "+usd);

                Intent intent = new Intent(ResultActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });

        /// save data
        saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(ResultActivity.this, StartActivity.class);
//                startActivity(intent);
//                finish();

                name = mEditText.getText().toString();


                System.out.println("save btn");
                MemoryHelper.getmHelper().saveData(
                        new UserData(
                                name,
                                step,
                                time
                        )
                );

                finish();


//                UserData usd = MemoryHelper.getmHelper().getData();
//                System.out.println("data: "+usd);
            }
        });
    }
}