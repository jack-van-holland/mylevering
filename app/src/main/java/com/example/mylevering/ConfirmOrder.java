package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ConfirmOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        MenuOption selected = (MenuOption) bundle.getSerializable("selected");
        Log.d("description", selected.description);
        Log.d("title", selected.title);
    }
}
