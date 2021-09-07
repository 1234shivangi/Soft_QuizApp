package com.shivangi.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputLayout Username , password ;
    Button Login , Go_To_Sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        View decorView = getWindow().getDecorView();
// Hide both the navigation bar and the status bar.
// SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
// a general rule, you should design your app to hide the status bar whenever you
// hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Username = findViewById(R.id.Login_Username);
        password = findViewById(R.id.Login_Password);
        Login = findViewById(R.id.Login);
        Go_To_Sign = findViewById(R.id.go_to_sign);

        Go_To_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }
    private Boolean validateUsername (){
        String val = Username.getEditText().getText().toString();
        if(val.isEmpty()){
            Username.setError("Field Cannot be empty");
            Username.setErrorEnabled(true);
            return false;
        }else
        {
            Username.setError(null);
            Username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        if(val.isEmpty())
        {
            password.setError("Field cannot be empty");
            password.setErrorEnabled(true);
            return false ;
        }else
        {
            password.setErrorEnabled(false);
            password.setError(null);
            return true;
        }

    }
    public void LoginUser(View view){

        if(!validatePassword() | !validateUsername())
        {
            return ;
        }else
        {
            isUser();
        }

    }

    private void isUser() {
        String EnteredName = Username.getEditText().getText().toString().trim();
        String EnteredPassword = password.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("name").equalTo(EnteredName);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Username.setError(null);
                    Username.setErrorEnabled(false);
                    String PasswordFromDB = snapshot.child(EnteredName).child("password").getValue(String.class);
                    if(PasswordFromDB.equals(EnteredPassword))
                    {
                        password.setError(null);
                        password.setErrorEnabled(false);
                        String NameFromDB = snapshot.child(EnteredName).child("name").getValue(String.class);
                        String NumberFromDB = snapshot.child(EnteredName).child("number").getValue(String.class);
                        String EmailFromDB = snapshot.child(EnteredName).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),Welcome.class);
                        intent.putExtra("name",NameFromDB);
                        intent.putExtra("number",NumberFromDB);
                        intent.putExtra("email",EmailFromDB);
                        startActivity(intent);
                    }
                    else{
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else{
                    Username.setError("No such user exists");
                    Username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}