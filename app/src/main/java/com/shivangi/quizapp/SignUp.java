package com.shivangi.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextInputLayout name , number , email , password, c_password;
    Button   Sign_up , Go_to_login;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_sign_up);
        name = findViewById(R.id.Sign_Name);
        number = findViewById(R.id.Sign_Number);
        email  =findViewById(R.id.Sign_Email);
        password = findViewById(R.id.Sign_Password);
        c_password = findViewById(R.id.Sign_C_Password);
        Sign_up = findViewById(R.id.sign_up);
        Go_to_login =findViewById(R.id.go_to_Login);

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();

                String Name = name.getEditText().getText().toString();
                String Number = number.getEditText().getText().toString();
                String Email = email.getEditText().getText().toString();
                String Password = password.getEditText().getText().toString();
                String Password_Confirmed = c_password.getEditText().getText().toString();
                reference = rootNode.getReference("users");
                UserHelperClass helperClass = new UserHelperClass(Name,Number,Email,Password,Password_Confirmed);
                reference.child(Name).setValue(helperClass);
            }
        });
        Go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
