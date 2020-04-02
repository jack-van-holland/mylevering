package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class FreshOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_order);
        LinearLayout fresh = findViewById(R.id.freshOptions);
        LinearLayout base = new LinearLayout(getApplicationContext());
        base.setOrientation(LinearLayout.VERTICAL);

        CheckBox[] checks = new CheckBox[FreshMenuOption.baseNames.length];
        for (int i = 0; i < FreshMenuOption.baseNames.length; i++){
            checks[i] = new CheckBox(getApplicationContext());
            checks[i].setText(FreshMenuOption.baseNames[i]);
            base.addView(checks[i]);
        }

        fresh.addView(base);

    }
}
