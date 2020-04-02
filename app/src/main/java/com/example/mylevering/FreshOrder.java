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

        final LinearLayout fresh = findViewById(R.id.freshOptions);
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
                    fresh.removeView(base);
                    baseToggle.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
                    baseShown.setText("False");
                } else {
                    fresh.addView(base, 1);
                    baseShown.setText("True");
                    baseToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
                }
            }
        });
        fresh.addView(baseHeader);
        base.addView(group);
        base.setPadding(50,0,0,0);
        fresh.addView(base);

        final LinearLayout spread = new LinearLayout(getApplicationContext());
        spread.setOrientation(LinearLayout.VERTICAL);

        CheckBox[] spreadChecks = new CheckBox[FreshMenuOption.spreadNames.length];
        for (int i = 0; i < FreshMenuOption.spreadNames.length; i++){
            spreadChecks[i] = new CheckBox(getApplicationContext());
            spreadChecks[i].setText(FreshMenuOption.spreadNames[i]);
            spread.addView(spreadChecks[i]);
        }
        final TextView spreadShown = new TextView(getApplicationContext());
        spreadShown.setText("True");
        TextView spreadTitle = new TextView(getApplicationContext());
        spreadTitle.setText(R.string.spreads);
        final ImageView spreadToggle = new ImageView(getApplicationContext());
        spreadToggle.setClickable(true);
        spreadToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);

        LinearLayout spreadHeader = new LinearLayout(getApplicationContext());
        spreadHeader.addView(spreadTitle);
        spreadHeader.addView(spreadToggle);
        spreadHeader.setPadding(50, 50,0,0);



        spreadToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("True".equals(spreadShown.getText().toString())) {
                    fresh.removeView(spread);
                    spreadToggle.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
                    spreadShown.setText("False");
                } else {
                    int aboveViews = 2;
                    if ("True".equals(baseShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    fresh.addView(spread, aboveViews);
                    spreadShown.setText("True");
                    spreadToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
                }
            }
        });
        fresh.addView(spreadHeader);
        spread.setPadding(50,0,0,0);
        fresh.addView(spread);

        final LinearLayout topping = new LinearLayout(getApplicationContext());
        topping.setOrientation(LinearLayout.VERTICAL);

        CheckBox[] toppingChecks = new CheckBox[FreshMenuOption.toppingNames.length];
        for (int i = 0; i < FreshMenuOption.toppingNames.length; i++){
            toppingChecks[i] = new CheckBox(getApplicationContext());
            toppingChecks[i].setText(FreshMenuOption.toppingNames[i]);
            topping.addView(toppingChecks[i]);
        }
        final TextView toppingShown = new TextView(getApplicationContext());
        toppingShown.setText("True");
        TextView toppingTitle = new TextView(getApplicationContext());
        toppingTitle.setText(R.string.toppings);
        final ImageView toppingToggle = new ImageView(getApplicationContext());
        toppingToggle.setClickable(true);
        toppingToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);

        LinearLayout toppingHeader = new LinearLayout(getApplicationContext());
        toppingHeader.addView(toppingTitle);
        toppingHeader.addView(toppingToggle);
        toppingHeader.setPadding(50, 50,0,0);



        toppingToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("True".equals(toppingShown.getText().toString())) {
                    fresh.removeView(topping);
                    toppingToggle.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
                    toppingShown.setText("False");
                } else {
                    int aboveViews = 3;
                    if ("True".equals(baseShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    if ("True".equals(spreadShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    fresh.addView(topping, aboveViews);
                    toppingShown.setText("True");
                    toppingToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
                }
            }
        });
        fresh.addView(toppingHeader);
        topping.setPadding(50,0,0,0);
        fresh.addView(topping);

    }
}
