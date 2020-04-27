package com.example.mylevering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LogIn extends AppCompatActivity {

    private static final String[] prefs = {"PREFS_NOTIFICATIONS_ALLOWED", "PREFS_SENT",
            "PREFS_IN_QUEUE", "PREFS_IN_PROGRESS", "PREFS_COMPLETE", "PREFS_DAIRY", "PREFS_GLUTEN",
            "PREFS_HALAL", "PREFS_KOSHER", "PREFS_NUT", "PREFS_SHELLFISH", "PREFS_VEGAN",
            "PREFS_VEGETARIAN"};

    private EditText username;
    private EditText pass;
    private ProgressBar progress;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        progress = findViewById(R.id.progress_bar);
    }

    public void completeLogIn(View v) {
        progress.setVisibility(View.VISIBLE);

        String usn = username.getText().toString();
        String ps = pass.getText().toString();

        if (usn.equals("") || ps.equals("")) {
            progress.setVisibility(View.GONE);
            String notFilled = "Missing fields";
            Toast.makeText(getApplicationContext(), notFilled, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!usn.contains("@jhu.edu")) {
            progress.setVisibility(View.GONE);
            String invalidEmail = "Invalid JHU email";
            Toast.makeText(getApplicationContext(), invalidEmail, Toast.LENGTH_SHORT).show();
            return;
        }

        auth.signInWithEmailAndPassword(usn, ps)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());
                        } else {
                            Toast.makeText(LogIn.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {
        // Go to MainActivity
        String name = user.getDisplayName().split(" ")[0];

        setResult(RESULT_OK);
        startActivity(new Intent(LogIn.this, MainActivity.class));
        finish();
        String success = "Welcome, " + name + "!";
        Toast.makeText(getApplicationContext(), success, Toast.LENGTH_SHORT).show();

        // load in preference info
        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = dbref.child("users").child(user.getUid()).child("prefs");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor pe = sp.edit();
                for (String pref : prefs) {
                    if (dataSnapshot.child(pref).getValue() != null) {
                        System.out.println("yeah baby");
                        pe.putBoolean(pref, (boolean) dataSnapshot.child(pref).getValue());
                    } else {
                        pe.putBoolean(pref, false);
                    }
                }
                pe.commit();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
