package com.example.android_lesson_1;

import static com.example.android_lesson_1.R.id.start_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startGame, resultGame, exitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startGame = findViewById(start_game);
        resultGame = findViewById(R.id.result_game);
        exitGame = findViewById(R.id.exit_game);

        startGame.setOnClickListener(this);
        resultGame.setOnClickListener(this);
        exitGame.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.start_game: {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.result_game: break;
            case R.id.exit_game: break;
        }

    }
}