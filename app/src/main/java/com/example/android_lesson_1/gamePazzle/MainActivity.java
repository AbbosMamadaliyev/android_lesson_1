package com.example.android_lesson_1.gamePazzle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_lesson_1.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> numbers = new ArrayList<>();
    Timer timer;
    private Toolbar mToolbar;
    private TextView timeView;
    ImageButton volumeBtn;
    private TextView stepView;
    private Button[][] mButtons = new Button[4][4];
    private MediaPlayer mMediaPlayer;
    private Button finishBtn;
    private Button restartBtn;
    private RelativeLayout buttonGroup;
    private int emptyI = 3;
    private int emptyJ = 3;
    private int step = 0;
    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puzzle15_example);

        mMediaPlayer = MediaPlayer.create(this, R.raw.swipe);

        loadNumbers();

        loadViews();

        setDataToView();

        createTimer();

        setListeners();
    }

    private void setListeners() {
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                finish();

                onBackPressed();
            }
        });

        restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartGame();
            }
        });


        volumeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }
        );
    }

    private void createTimer() {
        timer = null;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        time++;
                        timeCount(time);
                    }
                });
            }
        }, 1000, 1000);

    }

    private void setDataToView() {
        stepCount();
        System.out.println("text: " + mButtons[3][3].getText());

        for (int i = 0; i < 15; i++) {
            Button button = mButtons[i / 4][i % 4];
            button.setText(String.valueOf(numbers.get(i)));
            button.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_regular));

            button.setBackgroundResource(R.drawable.fill_button);
        }

        mButtons[emptyI][emptyJ].setBackgroundResource(R.drawable.empty_button);
    }

    private void loadViews() {
        finishBtn = findViewById(R.id.finishBtn);
        restartBtn = findViewById(R.id.restartBtn);
        timeView = findViewById(R.id.time_view);
        stepView = findViewById(R.id.step_view);

        volumeBtn = findViewById(R.id.volumeBtn);
        buttonGroup = findViewById(R.id.btn_group);
        /*
        00 01 02 03;
        10 11 12 13;
        20 21 22 23;
        30 31 32 33;      */
        for (int i = 0; i < 16; i++) {
            mButtons[i / 4][i % 4] = (Button) buttonGroup.getChildAt(i);

        }
    }


    public void loadNumbers() {
        numbers.clear();

        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }

//        Collections.shuffle(numbers);
    }

    public void buttonClick(View view) {
        Button button = (Button) view;
        String tag = button.getTag().toString();
        String[] indexes = tag.split(":");

        int i = Integer.parseInt(indexes[0]);
        int j = Integer.parseInt(indexes[1]);

        int deltaI = Math.abs(i - emptyI);
        int deltaJ = Math.abs(j - emptyJ);

        if (((deltaI == 1 && deltaJ == 0) || (deltaJ == 1 && deltaI == 0))) {
            mButtons[emptyI][emptyJ].setText(button.getText());
            mButtons[emptyI][emptyJ].setBackground(button.getBackground());

            button.setText("");
            button.setBackgroundResource(R.drawable.empty_button);

            emptyI = i;
            emptyJ = j;

            step++;

            stepCount();

            mMediaPlayer.start();

            if (emptyI == 3 && emptyJ == 3) {
                checkToWin();
            }
        } else {
            Toast.makeText(this, tag, Toast.LENGTH_SHORT).show();
        }

    }

    private void stepCount() {
        stepView.setText(String.valueOf(step));
        stepView.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_regular));
    }

    private void timeCount(int time) {
        int hour = time / 3600;
        int minute = time % 3600 / 60;
        int second = time % 60;

        timeView.setText(String.format("%02d:%02d:%02d", hour, minute, second));
    }

    private void checkToWin() {
        boolean isWin = true;

        for (int i = 0; i < 15; i++) {
            Button button = (Button) buttonGroup.getChildAt(i);
            if (!button.getText().toString().equals(i + 1 + "")) {
                isWin = false;
                break;
            }
        }

        if (isWin) {
            timer.cancel();

            showAlertDialog();
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Win!!!").setMessage("You are winner");

        dialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("time", time);
                intent.putExtra("step", step);

                startActivity(intent);

                finish();


            }
        });
        dialogBuilder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        });

        dialogBuilder.create().show();
    }

    private void restartGame() {
        timer.cancel();

        time = 0;
        step = 0;
        emptyJ = 3;
        emptyI = 3;
        mButtons[emptyI][emptyJ].setText("");

        timeCount(time);
        stepCount();

        createTimer();
        loadNumbers();
        setDataToView();
    }


   /*
    //  --- for profile ui activity

    public void homeClick(View view) {

        Toast.makeText(this, "Home is clicked", Toast.LENGTH_SHORT).show();
    }
    public void contactClick(View view) {

        Toast.makeText(this, "Contact is clicked", Toast.LENGTH_SHORT).show();
    }
    public void profileClick(View view) {

        Toast.makeText(this, "Profile page is clicked", Toast.LENGTH_SHORT).show();
    }*/
}