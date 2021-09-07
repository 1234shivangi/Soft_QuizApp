package com.shivangi.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        final AppCompatButton StartNewBtn = findViewById(R.id.StartNewQuiz);
        final TextView correctAnswer = findViewById(R.id.correctAnswer);
        final TextView IncorrectAnswer = findViewById(R.id.incorrectAnswer);

        final int getCorrectAnswer = getIntent().getIntExtra("correct", 0);
        final int getIncorrectAnswer = getIntent().getIntExtra("Incorrect", 0);

        correctAnswer.setText(String.valueOf(getCorrectAnswer));
        IncorrectAnswer.setText(String.valueOf(getIncorrectAnswer));

        StartNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result.this, Welcome.class));
                finish();
            }
        });
    }
}
