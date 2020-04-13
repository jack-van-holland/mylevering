package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeLanding extends AppCompatActivity {

    public static final int LOG_IN = 0;

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
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == LOG_IN) {
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}