package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.resources.TextAppearance;

public class FreshOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_order);
        LinearLayout fresh = findViewById(R.id.freshOptions);
        final LinearLayout base = new LinearLayout(getApplicationContext());
        base.setOrientation(LinearLayout.VERTICAL);

        RadioGroup group = new RadioGroup(getApplicationContext());
        RadioButton[] buttons = new RadioButton[FreshMenuOption.baseNames.length];
        for (int i = 0; i < FreshMenuOption.baseNames.length; i++){
            buttons[i] = new RadioButton(getApplicationContext());
            buttons[i].setText(FreshMenuOption.baseNames[i]);
            group.addView(buttons[i]);
        }
        final TextView baseShown = new TextView(getApplicationContext());
        baseShown.setText("True");
        TextView baseTitle = new TextView(getApplicationContext());
        baseTitle.setText(R.string.base);
        final ImageView baseToggle = new ImageView(getApplicationContext());
        baseToggle.setClickable(true);
        baseToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);

        LinearLayout baseHeader = new LinearLayout(getApplicationContext());
        baseHeader.addView(baseTitle);
        baseHeader.addView(baseToggle);
        baseHeader.setPadding(50, 50,0,0);



        baseToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("True".equals(baseShown.getText().toString())) {
                    base.setVisibility(View.INVISIBLE);
                    baseToggle.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
                    baseShown.setText("False");
                } else {
                    base.setVisibility(View.VISIBLE);
                    baseShown.setText("True");
                    baseToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
                }
            }
        });
        fresh.addView(baseHeader);
        base.addView(group);
        base.setPadding(50,0,0,0);
        fresh.addView(base);



    }
}
