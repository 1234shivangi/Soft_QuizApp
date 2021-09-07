package com.shivangi.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    TextView Name , Number , Email , Message_Us ,Call_Us ;
    Button Instagram,FaceBook, Start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        Email = findViewById(R.id.welcome_email);
        Name = findViewById(R.id.welcome_name);
        Number = findViewById(R.id.welcome_number);
        Instagram = findViewById(R.id.Instagram);
        FaceBook = findViewById(R.id.FaceBook);
        Message_Us = findViewById(R.id.Email);
        Call_Us = findViewById(R.id.Call);
        Start = findViewById(R.id.Start_Quiz);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Question.class);
                startActivity(intent);
            }
        });

        Message_Us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW
                        , Uri.parse("mailto:" + "test@gmail.com"));
                startActivity(intent);
            }
        });
        Call_Us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }


        });
        FaceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.facebook.com/");
            }
            public void gotoUrl(String s) {

                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });
        Instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.instagram.com/");
            }

            public void gotoUrl(String s) {

                Uri uri = Uri.parse(s);
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        showAll();
    }

    private void showAll() {
        Intent intent = getIntent();
        String userName = intent.getStringExtra("name");
        String userNumber = intent.getStringExtra("number");
        String userEmail = intent.getStringExtra("email");
        Email.setText(userEmail);
        Name.setText(userName);
        Number.setText(userNumber);
    }
    private void makePhoneCall() {
        String number = "111111111";
        if(number.trim().length()>0)
        {
            if(ContextCompat.checkSelfPermission(Welcome.this,
                    Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(Welcome.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }else{
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}