package com.example.mylevering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;


public class SignUp extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText password2;

    private String fn;
    private String ln;
    private String em;
    private String pwd;
    private String pwd2;

    private ProgressBar progress;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        password2 = findViewById(R.id.password2);
        progress = findViewById(R.id.progress_bar);
    }

    public void completeSignUp(View v) {
        progress.setVisibility(View.VISIBLE);

        fn = firstName.getText().toString();
        ln = lastName.getText().toString();
        em = email.getText().toString();
        pwd = password.getText().toString();
        pwd2 = password2.getText().toString();

        if (fn.equals("") || ln.equals("") || em.equals("") || pwd.equals("") || pwd2.equals("")) {
            progress.setVisibility(View.GONE);
            String notFilled = "Missing fields";
            Toast.makeText(getApplicationContext(), notFilled, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!em.contains("@jhu.edu")) {
            progress.setVisibility(View.GONE);
            String invalidEmail = "Invalid JHU email";
            Toast.makeText(getApplicationContext(), invalidEmail, Toast.LENGTH_SHORT).show();
            return;
        }
        if (pwd.length() < 6) {
            progress.setVisibility(View.GONE);
            String minChar = "Password must be at least 6 characters";
            Toast.makeText(getApplicationContext(), minChar, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(pwd2)) {
            progress.setVisibility(View.GONE);
            String pwdMismatch = "Passwords do not match";
            Toast.makeText(getApplicationContext(), pwdMismatch, Toast.LENGTH_SHORT).show();
            return;
        }

        auth.createUserWithEmailAndPassword(em, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(SignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        // Add name field to user
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fn + " " + ln).build();
        user.updateProfile(profileUpdates);

        // Sign out so user can log in with created information
        auth.signOut();

        // Go to landing page to log in with created account
        startActivity(new Intent(SignUp.this, WelcomeLanding.class));
        finish();
        String success = "Successfully signed up!";
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();
    }

}
