package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mylevering.ui.login.LoginActivity;

public class WelcomeLanding extends AppCompatActivity {

    public static final int LOG_IN = 0;
    public static final int SIGN_UP = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void launchLogIn(View v) {
        Intent intent = new Intent(this, LogIn.class);
        startActivityForResult(intent, LOG_IN);
    }

    public void launchSignUp(View v) {
        Intent intent = new Intent(this, SignUp.class);
        startActivityForResult(intent, SIGN_UP);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}