package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText email;
    private EditText password;
    private EditText password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
    }

    public void completeSignUp(View v) {
        String fn = firstName.getText().toString();
        String ln = lastName.getText().toString();
        String em = email.getText().toString();
        String pwd = password.getText().toString();
        String pwd2 = password2.getText().toString();

        if (fn.equals("") || ln.equals("") || em.equals("") || pwd.equals("") || pwd2.equals("")) {
            String notFilled = "Please fill in all the fields above";
            Toast.makeText(getApplicationContext(), notFilled, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!em.contains("@jhu.edu")) {
            String invalidEmail = "Invalid JHU email";
            Toast.makeText(getApplicationContext(), invalidEmail, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pwd.equals(pwd2)) {
            String pwdMismatch = "Passwords do not match";
            Toast.makeText(getApplicationContext(), pwdMismatch, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("firstName", fn);
        intent.putExtra("lastName", ln);
        intent.putExtra("email", em);
        intent.putExtra("pwd", pwd);
        startActivity(intent);

        String welcome = "Welcome, " + fn + "!";
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_SHORT).show();
    }
}
