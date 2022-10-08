package com.example.android_lesson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private Button backToHomeBtn;
    private TextView resTime;
    private TextView resStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        int step = intent.getIntExtra("step", 0);
        int time = intent.getIntExtra("time", 0);


        backToHomeBtn = findViewById(R.id.result_home_btn);
        resStep = findViewById(R.id.result_step);
        resTime = findViewById(R.id.result_time);

        resStep.setText(String.format("Your step: %d", step));
        resTime.setText(String.format("Your time: %d", time));

        setClickListener();
    }

    private void setClickListener() {
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}