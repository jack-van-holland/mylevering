package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText pass;
    private Button login;

    private DatabaseReference dbref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        dbref = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);

        login.setOnClickListener(this);
    }

    public void completeLogIn(View v) {
        String usn = username.getText().toString();
        String ps = pass.getText().toString();

        if (usn.equals("") || ps.equals("")) {
            String notFilled = "Missing fields";
            Toast.makeText(getApplicationContext(), notFilled, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!usn.contains("@jhu.edu")) {
            String invalidEmail = "Invalid JHU email";
            Toast.makeText(getApplicationContext(), invalidEmail, Toast.LENGTH_SHORT).show();
            return;
        }

        //TODO: check database to see if username & password combination exists

        setResult(RESULT_OK);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
