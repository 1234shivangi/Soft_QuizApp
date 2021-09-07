package com.shivangi.quizapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Question extends AppCompatActivity {

    TextView question ,questions, timer ;
    AppCompatButton option1 , option2  ,option3 , option4 , next;
    private Timer quizTimer;
    private  int totalTimerInMins  = 1;
    private int seconds = 0 ;
    private  List<QuestionsList> questionsLists ;
    private String selectedOptionByUser = "" ;
    private int currentQuestionsPosition = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        question = findViewById(R.id.question);
        questions = findViewById(R.id.questions);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        next = findViewById(R.id.next);
        timer = findViewById(R.id.timer);
        StartTimer(timer);
        questionsLists = QuestionBank.getQuestions("java");
        questions.setText((currentQuestionsPosition + 1 ) +"/" + questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());
        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty())
                {
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_red);
                    option1.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty())
                {
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_red);
                    option2.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty())
                {
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_red);
                    option3.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty())
                {
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_red);
                    option4.setTextColor(Color.WHITE);
                    revealAnswer();
                    questionsLists.get(currentQuestionsPosition).setUserSelectedAnswer(selectedOptionByUser);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedOptionByUser.isEmpty()){
                    Toast.makeText(Question.this,"Please select an option",Toast.LENGTH_SHORT).show();
                }
                else{
                    changeNextQuestion();
                }
            }
        });

    }

    private void changeNextQuestion(){
        currentQuestionsPosition++;
        if(currentQuestionsPosition+1==questionsLists.size())
        {
            next.setText("SUBMIT");
        }
        if(currentQuestionsPosition < questionsLists.size()){
            selectedOptionByUser = "";

            option1.setBackgroundResource(R.drawable.round_white);
            option1.setTextColor(Color.parseColor("#1F6BB8"));

            option2.setBackgroundResource(R.drawable.round_white);
            option2.setTextColor(Color.parseColor("#1F6BB8"));

            option3.setBackgroundResource(R.drawable.round_white);
            option3.setTextColor(Color.parseColor("#1F6BB8"));

            option4.setBackgroundResource(R.drawable.round_white);
            option4.setTextColor(Color.parseColor("#1F6BB8"));

            questions.setText((currentQuestionsPosition + 1 ) +"/" + questionsLists.size());
            question.setText(questionsLists.get(currentQuestionsPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionsPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionsPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionsPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionsPosition).getOption4());
        }
        else
        {
            Intent intent = new Intent(Question.this,Result.class);
            intent.putExtra("correct",getCorrectAnswer());
            intent.putExtra("incorrect",getIncorrectAnswer());
            startActivity(intent);
            finish();
        }
    }
    private  void StartTimer(TextView timerTextView)
    {
        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(seconds==0){
                    totalTimerInMins--;
                    seconds = 59;
                }
                else if(seconds == 0 &&totalTimerInMins == 0 ){
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(Question.this,"Time Over",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Question.this,Result.class);
                    intent.putExtra("correct",getCorrectAnswer() );
                    intent.putExtra("incorrect",getIncorrectAnswer());
                    startActivity(intent);
                    finish();
                }
                else
                {
                    seconds--;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String finalMinutes = String.valueOf(totalTimerInMins);
                        String finalSeconds = String.valueOf(seconds);

                        if(finalMinutes.length()==1)
                        {
                            finalMinutes = "0" +finalMinutes;
                        }
                        if(finalSeconds.length()== 1 ){
                            finalSeconds = "0" + finalSeconds;
                        }
                        timerTextView.setText(finalMinutes + ":" +finalSeconds);
                    }
                });
            }
        },1000,1000);
    }

    private int getCorrectAnswer(){
        int correctAnswers = 0 ;

        for(int i= 0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(getUserSelectedAnswer.equals(getAnswer))
            {
                correctAnswers++;
            }
        }
        return correctAnswers;

    }

    private int getIncorrectAnswer(){
        int correctAnswers = 0 ;

        for(int i= 0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getAnswer();

            if(!getUserSelectedAnswer.equals(getAnswer))
            {
                correctAnswers++;
            }
        }
        return correctAnswers;

    }

    @Override
    public void onBackPressed() {
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(Question.this,Welcome.class));
        finish();
    }

    private void revealAnswer(){
        final String getAnswer = questionsLists.get(currentQuestionsPosition).getAnswer();

        if(option1.getText().toString().equals(getAnswer))
        {
            option1.setBackgroundResource(R.drawable.round_green);
            option1.setTextColor(Color.WHITE);
        }
        else if(option2.getText().toString().equals(getAnswer)){
            option2.setBackgroundResource(R.drawable.round_green);
            option2.setTextColor(Color.WHITE);
        }else if(option3.getText().toString().equals(getAnswer))
        {
            option3.setBackgroundResource(R.drawable.round_green);
            option3.setTextColor(Color.WHITE);
        } else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_green);
            option4.setTextColor(Color.WHITE);
        }
    }
}