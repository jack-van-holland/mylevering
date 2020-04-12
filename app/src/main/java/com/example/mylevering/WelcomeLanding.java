package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylevering.ui.login.LoginActivity;

public class WelcomeLanding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void launchLogIn(View v) {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void launchSignUp(View v) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }
}