package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ButterflyMenuItem extends AppCompatActivity {
    private ButterflyMenuOption order;

    private boolean orderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly_menu_item);

        TextView itemTitle = findViewById(R.id.menu_item_title);
        TextView itemPrice = findViewById(R.id.menu_item_price);
        TextView itemIngredients = findViewById(R.id.menu_item_ingredients);
        TextView itemNutrition = findViewById(R.id.menu_item_nutrition);
        EditText itemInstruction = findViewById(R.id.special_instructions);
        Button orderBtn = findViewById(R.id.orderButton);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final ButterflyMenuOption order = (ButterflyMenuOption) bundle.getSerializable("selected");

        //if(getIntent().hasExtra("menu_item_selected")) {
        if (order != null) {
            //order = getIntent().getParcelableExtra("menu_item_selected");
            itemTitle.setText(order.getTitle());
            itemPrice.setText(order.getPrice());
            itemNutrition.setText("Calories: " + order.getCalories());
            //String itemDescriptionandNote = order.getDescription();
            //itemDescriptionandNote += itemInstruction.getText().toString();
            String note = itemInstruction.getText().toString();
            order.addDescriptionNote(note);
            itemIngredients.setText(order.getDescription());

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            orderable = sp.getBoolean("orderable", true);

            if (!orderable) {
                orderBtn.setBackgroundColor(getResources().getColor(R.color.colorGray));
            }

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (orderable) {
                        Intent intent = new Intent(ButterflyMenuItem.this, ConfirmOrder.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("selected", order);
                        intent.putExtras(bundle);
                        intent.putExtra("from", "Butterfly");
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
}
