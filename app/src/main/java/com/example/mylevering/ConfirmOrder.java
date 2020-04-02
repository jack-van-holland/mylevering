package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

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
        TextView txt = findViewById(R.id.textView);
        txt.setText(Html.fromHtml(selected.description));
    }
}
