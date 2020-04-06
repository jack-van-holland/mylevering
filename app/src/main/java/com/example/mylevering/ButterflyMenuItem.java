package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ButterflyMenuItem extends AppCompatActivity {
    private ButterflyMenuOption order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly_menu_item);

        TextView itemTitle = findViewById(R.id.menu_item_title);
        TextView itemPrice = findViewById(R.id.menu_item_price);
        Button orderBtn = findViewById(R.id.orderButton);

        if(getIntent().hasExtra("menu_item_selected")) {
            order = getIntent().getParcelableExtra("menu_item_selected");
            itemTitle.setText(order.getTitle());
            itemPrice.setText(order.getPrice());

            orderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO pass to Confirm Order page
                    Intent intent = new Intent(ButterflyMenuItem.this, ConfirmOrder.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("selected", order);
                    intent.putExtras(bundle);
                    intent.putExtra("from", "Butterfly");
                    startActivity(intent);
                }
            });
        }
    }
}
