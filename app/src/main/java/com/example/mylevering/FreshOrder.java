package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FreshOrder extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_order);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        FreshMenuOption pastOrder = null;
        if(bundle != null && bundle.get("selected") != null) {
            pastOrder = (FreshMenuOption) bundle.getSerializable("selected");
        }
        final LinearLayout fresh = findViewById(R.id.freshOptions);
        final LinearLayout base = new LinearLayout(getApplicationContext());
        base.setOrientation(LinearLayout.VERTICAL);

        final RadioGroup baseGroup = new RadioGroup(getApplicationContext());
        final RadioButton[] baseButtons = new RadioButton[FreshMenuOption.baseNames.length];
        for (int i = 0; i < FreshMenuOption.baseNames.length; i++){
            baseButtons[i] = new RadioButton(getApplicationContext());
            baseButtons[i].setText(FreshMenuOption.baseNames[i]);
            baseButtons[i].setId(View.generateViewId());
            baseGroup.addView(baseButtons[i]);
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

        fresh.addView(baseHeader);
        base.addView(baseGroup);
        base.setPadding(50,0,0,0);
        fresh.addView(base);

        final LinearLayout spread = new LinearLayout(getApplicationContext());
        spread.setOrientation(LinearLayout.VERTICAL);

        final CheckBox[] spreadChecks = new CheckBox[FreshMenuOption.spreadNames.length];
        for (int i = 0; i < FreshMenuOption.spreadNames.length; i++){
            spreadChecks[i] = new CheckBox(getApplicationContext());
            spreadChecks[i].setText(FreshMenuOption.spreadNames[i]);
            spreadChecks[i].setId(View.generateViewId());
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

        fresh.addView(spreadHeader);
        spread.setPadding(50,0,0,0);
        fresh.addView(spread);

        final LinearLayout topping = new LinearLayout(getApplicationContext());
        topping.setOrientation(LinearLayout.VERTICAL);

        final CheckBox[] toppingChecks = new CheckBox[FreshMenuOption.toppingNames.length];
        for (int i = 0; i < FreshMenuOption.toppingNames.length; i++){
            toppingChecks[i] = new CheckBox(getApplicationContext());
            toppingChecks[i].setText(FreshMenuOption.toppingNames[i]);
            toppingChecks[i].setId(View.generateViewId());
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

        fresh.addView(toppingHeader);
        topping.setPadding(50,0,0,0);
        fresh.addView(topping);

        final LinearLayout protein = new LinearLayout(getApplicationContext());
        protein.setOrientation(LinearLayout.VERTICAL);

        final RadioGroup proteinGroup = new RadioGroup(getApplicationContext());
        final RadioButton[] proteinButtons = new RadioButton[FreshMenuOption.proteinNames.length];
        for (int i = 0; i < FreshMenuOption.proteinNames.length; i++){
            proteinButtons[i] = new RadioButton(getApplicationContext());
            proteinButtons[i].setText(FreshMenuOption.proteinNames[i]);
            proteinButtons[i].setId(View.generateViewId());
            proteinGroup.addView(proteinButtons[i]);
        }
        final TextView proteinShown = new TextView(getApplicationContext());
        proteinShown.setText("True");
        TextView proteinTitle = new TextView(getApplicationContext());
        proteinTitle.setText(R.string.protein);
        final ImageView proteinToggle = new ImageView(getApplicationContext());
        proteinToggle.setClickable(true);
        proteinToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);

        LinearLayout proteinHeader = new LinearLayout(getApplicationContext());
        proteinHeader.addView(proteinTitle);
        proteinHeader.addView(proteinToggle);
        proteinHeader.setPadding(50, 50,0,0);


        fresh.addView(proteinHeader);
        protein.addView(proteinGroup);
        protein.setPadding(50,0,0,0);
        fresh.addView(protein);

        final LinearLayout dressing = new LinearLayout(getApplicationContext());
        dressing.setOrientation(LinearLayout.VERTICAL);

        final RadioGroup dressingGroup = new RadioGroup(getApplicationContext());
        final RadioButton[] dressingButtons = new RadioButton[FreshMenuOption.dressingNames.length];
        for (int i = 0; i < FreshMenuOption.dressingNames.length; i++){
            dressingButtons[i] = new RadioButton(getApplicationContext());
            dressingButtons[i].setText(FreshMenuOption.dressingNames[i]);
            dressingButtons[i].setId(View.generateViewId());
            dressingGroup.addView(dressingButtons[i]);
        }
        final TextView dressingShown = new TextView(getApplicationContext());
        dressingShown.setText("True");
        TextView dressingTitle = new TextView(getApplicationContext());
        dressingTitle.setText(R.string.dressing);
        final ImageView dressingToggle = new ImageView(getApplicationContext());
        dressingToggle.setClickable(true);
        dressingToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);

        LinearLayout dressingHeader = new LinearLayout(getApplicationContext());
        dressingHeader.addView(dressingTitle);
        dressingHeader.addView(dressingToggle);
        dressingHeader.setPadding(50, 50,0,0);


        fresh.addView(dressingHeader);
        dressing.addView(dressingGroup);
        dressing.setPadding(50,0,0,0);
        fresh.addView(dressing);

        final EditText instructions = new EditText(getApplicationContext());
        instructions.setHint(R.string.butterfly_customize);
        instructions.setPadding(50, 0, 0, 25);

        TextView txt = new TextView((getApplicationContext()));
        txt.setText(R.string.butterfly_instructions);
        txt.setPadding(50,50,0,0);

        fresh.addView(txt);
        fresh.addView(instructions);

        final Button orderButton = findViewById(R.id.orderButton);
        //orderButton.setClickable(false);


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

        proteinToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("True".equals(proteinShown.getText().toString())) {
                    fresh.removeView(protein);
                    proteinToggle.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
                    proteinShown.setText("False");
                } else {
                    int aboveViews = 4;
                    if ("True".equals(baseShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    if ("True".equals(spreadShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    if ("True".equals(toppingShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    fresh.addView(protein, aboveViews);
                    proteinShown.setText("True");
                    proteinToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
                }
            }
        });

        dressingToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("True".equals(dressingShown.getText().toString())) {
                    fresh.removeView(dressing);
                    dressingToggle.setImageResource(R.drawable.baseline_arrow_drop_down_black_18dp);
                    dressingShown.setText("False");
                } else {
                    int aboveViews = 5;
                    if ("True".equals(baseShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    if ("True".equals(spreadShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    if ("True".equals(toppingShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    if ("True".equals(proteinShown.getText().toString())) {
                        aboveViews += 1;
                    }
                    fresh.addView(dressing, aboveViews);
                    dressingShown.setText("True");
                    dressingToggle.setImageResource(R.drawable.baseline_arrow_drop_up_black_18dp);
                }
            }
        });

        //changeButtons(orderButton, baseGroup, spreadChecks, toppingChecks, proteinGroup, dressingGroup);
        View.OnClickListener checkSelection = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeButtons(orderButton, baseButtons, spreadChecks, toppingChecks, proteinButtons, dressingButtons, instructions);
            }
        };

        for (int i = 0; i < baseButtons.length; i++) {
            baseButtons[i].setOnClickListener(checkSelection);
        }
        for (int i = 0; i < spreadChecks.length; i++) {
            spreadChecks[i].setOnClickListener(checkSelection);
        }
        for (int i = 0; i < toppingChecks.length; i++) {
            toppingChecks[i].setOnClickListener(checkSelection);
        }
        for (int i = 0; i < proteinButtons.length; i++) {
            proteinButtons[i].setOnClickListener(checkSelection);
        }
        for (int i = 0; i < dressingButtons.length; i++) {
            dressingButtons[i].setOnClickListener(checkSelection);
        }

        populateOptions(orderButton, baseButtons, spreadChecks, toppingChecks, proteinButtons, dressingButtons, instructions, pastOrder);
    }

    public FreshMenuOption getChoices(RadioButton[] baseButtons, CheckBox[] spreadChecks, CheckBox[] toppingChecks,
                                      RadioButton[] proteinButtons, RadioButton[] dressingButtons, EditText instructions) {
        int base = -1;
        for (int i = 0; i < baseButtons.length; i++) {
            if(baseButtons[i].isChecked()) {
                base = i;
            }
        }
        int[] spreads = new int[spreadChecks.length];
        int spreadCount = 0;
        for (int i = 0; i < spreadChecks.length; i++) {
            if(spreadChecks[i].isChecked()) {
                spreads[i] = 1;
                spreadCount++;
            }
        }
        int[] toppings = new int[toppingChecks.length];
        int toppingCount = 0;
        for (int i = 0; i < toppingChecks.length; i++) {
            if(toppingChecks[i].isChecked()) {
                toppings[i] = 1;
                toppingCount++;
            }
        }
        int protein = -1;
        for (int i = 0; i < proteinButtons.length; i++) {
            if(proteinButtons[i].isChecked()) {
                protein = i;
            }
        }
        int dressing = -1;
        for (int i = 0; i < dressingButtons.length; i++) {
            if(dressingButtons[i].isChecked()) {
                dressing = i;
            }
        }
        FreshMenuOption selected = new FreshMenuOption(base, spreads, spreadCount, toppings, toppingCount, protein, dressing);
        if (!instructions.getText().toString().equals("")) {
            selected.setInstructions(("<br><b>Special Instructions</b>: ").concat(instructions.getText().toString()));
        }
        return selected;
    }

    public boolean validateChoices(RadioButton[] baseGroup, CheckBox[] spreadChecks, CheckBox[] toppingChecks,
                                   RadioButton[] proteinGroup, RadioButton[] dressingGroup) {
        boolean base = false;
        boolean spread = false;
        int spreadCount = 0;
        boolean topping = false;
        int toppingCount = 0;
        boolean protein = false;
        boolean dressing = false;
        for(int i = 0; i < baseGroup.length; i++) {
            if (baseGroup[i].isChecked()) {
                base = true;
            }
        }

        for (int i = 0; i < spreadChecks.length; i++) {
            if(spreadChecks[i].isChecked()) {
                spreadCount++;
            }
        }

        if (spreadCount <= 3 && spreadCount >= 1) {
            spread = true;
        }

        for (int i = 0; i < toppingChecks.length; i++) {
            if(toppingChecks[i].isChecked()) {
                toppingCount++;
            }
        }

        if (toppingCount <= 5 && toppingCount >= 1) {
            topping = true;
        }

        for(int i = 0; i < proteinGroup.length; i++) {
            if (proteinGroup[i].isChecked()) {
                protein = true;
            }
        }

        for(int i = 0; i < dressingGroup.length; i++) {
            if (dressingGroup[i].isChecked()) {
                dressing = true;
            }
        }
        return base && spread && topping && protein && dressing;
    }

    public void changeButtons(Button button, final RadioButton[] baseButtons, final CheckBox[] spreadChecks, final CheckBox[] toppingChecks,
                              final RadioButton[] proteinButtons, final RadioButton[] dressingButtons, final EditText instructions) {
        if (spreadChecks[spreadChecks.length - 1].isChecked()) {
            for (int i = 0; i < spreadChecks.length - 1; i++) {
                spreadChecks[i].setChecked(false);
                spreadChecks[i].setClickable(false);
                spreadChecks[i].setTextColor(getResources().getColor(R.color.colorGray));
            }
        } else {
            for (int i = 0; i < spreadChecks.length - 1; i++) {
                spreadChecks[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                spreadChecks[i].setClickable(true);
            }
        }

        if (toppingChecks[toppingChecks.length - 1].isChecked()) {
            for (int i = 0; i < toppingChecks.length - 1; i++) {
                toppingChecks[i].setChecked(false);
                toppingChecks[i].setClickable(false);
                toppingChecks[i].setTextColor(getResources().getColor(R.color.colorGray));
            }
        } else {
            for (int i = 0; i < toppingChecks.length - 1; i++) {
                toppingChecks[i].setClickable(true);
                toppingChecks[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }

        if(validateChoices(baseButtons, spreadChecks, toppingChecks, proteinButtons, dressingButtons)) {
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            button.setClickable(true);
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            boolean orderable = sp.getBoolean("orderable", true);

            if (!orderable) {
                button.setBackgroundColor(getResources().getColor(R.color.colorGray));
            }
        } else {
            button.setBackgroundColor(getResources().getColor(R.color.colorGray));
            button.setClickable(false);
        }

        if (button.isClickable()) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    boolean orderable = sp.getBoolean("orderable", true);
                    if (orderable) {
                        MenuOption selected;
                        selected = getChoices(baseButtons, spreadChecks, toppingChecks, proteinButtons, dressingButtons, instructions);
                        Intent intent = new Intent(FreshOrder.this, ConfirmOrder.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selected", selected);
                        intent.putExtras(bundle);
                        intent.putExtra("from", "Fresh");
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Pick up your order before ordering again.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
        }
    }
    private void populateOptions(Button button, final RadioButton[] baseButtons, final CheckBox[] spreadChecks, final CheckBox[] toppingChecks,
                                 final RadioButton[] proteinButtons, final RadioButton[] dressingButtons, final EditText instructions, FreshMenuOption past) {
        if (past == null) {
            return;
        }
        int base = past.getBase();
        baseButtons[base].setChecked(true);
        ArrayList<Integer> spreads = past.getSpreads();
        for (Integer j : spreads) {
            spreadChecks[j].setChecked(true);
        }

        ArrayList<Integer> toppings = past.getToppings();
        for (Integer k : toppings) {
            toppingChecks[k].setChecked(true);
        }
        int protein = past.getProtein();
        proteinButtons[protein].setChecked(true);

        int dressing = past.getDressing();
        dressingButtons[dressing].setChecked(true);

        String instr = past.getInstructions();
        if (!instr.equals("")) {
            instr = instr.substring(instr.indexOf("</b>:") + 6);
        }
        instructions.setText(instr);
        changeButtons(button, baseButtons, spreadChecks, toppingChecks, proteinButtons, dressingButtons, instructions);
    }
}
