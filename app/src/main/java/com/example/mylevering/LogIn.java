package com.example.mylevering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogIn extends AppCompatActivity {

    private EditText username;
    private EditText pass;

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

        auth.signInWithEmailAndPassword(usn, ps)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(LogIn.this, "Sign In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        // Write new user
        String userId = user.getUid();
        User us = new User(userId, fn, ln, em, pwd);
        dbref.child("users").child(userId).setValue(us);

        // Go to MainActivity
        setResult(RESULT_OK);
        startActivity(new Intent(LogIn.this, MainActivity.class));
        finish();
        String success = "Successfully logged in!";
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
    }

}
