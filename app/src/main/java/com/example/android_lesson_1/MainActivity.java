package com.example.android_lesson_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void homeClick(View view) {

        Toast.makeText(this, "Home is clicked", Toast.LENGTH_SHORT).show();
    }
    public void contactClick(View view) {

        Toast.makeText(this, "Contact is clicked", Toast.LENGTH_SHORT).show();
    }
    public void profileClick(View view) {

        Toast.makeText(this, "Profile page is clicked", Toast.LENGTH_SHORT).show();
    }
}