package com.example.android_lesson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_lesson_1.cache.MemoryHelper;
import com.example.android_lesson_1.models.UserData;

import java.util.Objects;

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


        backToHomeBtn = findViewById(R.id.result_home_btn);
        saveDataBtn = findViewById(R.id.save_result);
        resStep = findViewById(R.id.result_step);
        resTime = findViewById(R.id.result_time);
        mEditText = findViewById(R.id.edit_name);


        resStep.setText(String.format("Your step: %d", step));
        resTime.setText(String.format("Your time: %d", time));

        setClickListener(step, time);
    }

    private void setClickListener( int step, int time) {

        /// navigate to home
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData usd = MemoryHelper.getmHelper().getData();
                System.out.println("data1: "+usd);

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

                UserData usd = MemoryHelper.getmHelper().getData();
                System.out.println("data: "+usd);
            }
        });
    }
}