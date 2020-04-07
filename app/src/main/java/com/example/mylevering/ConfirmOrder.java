package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ConfirmOrder extends AppCompatActivity {

    private MenuOption selected;
    private ImageView heart;
    private ImageView heartFilled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        // Enable the back button. I need to do it this way because we could get this activity from
        // multiple sources.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        selected = (MenuOption) bundle.getSerializable("selected");
        String from = bundle.getString("from");
        Log.d("description", selected.getDescription());
        Log.d("title", selected.getTitle());
        TextView desc = findViewById(R.id.confirm_description);
        TextView title = findViewById(R.id.confirm_title);
        TextView price = findViewById(R.id.confirm_price);
        TextView totalPrice = findViewById(R.id.confirm_total_price);
        TextView type = findViewById(R.id.confirm_category);
        desc.setText(Html.fromHtml(selected.getDescription()));
        title.setText(selected.getTitle());
        price.setText(selected.getPrice());
        totalPrice.setText(selected.getPrice());

        ImageView fresh = findViewById(R.id.confirm_fresh_image);
        ImageView butterfly = findViewById(R.id.confirm_butterfly_image);
        type.setText(from);
        if (from.equals("Fresh")) {
            fresh.setVisibility(View.VISIBLE);
            butterfly.setVisibility(View.INVISIBLE);
        } else {
            fresh.setVisibility(View.INVISIBLE);
            butterfly.setVisibility(View.VISIBLE);
        }

        ImageView trash = findViewById(R.id.confirm_trash);
        trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this , MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView pencil = findViewById(R.id.confirm_pencil);
        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        heart = findViewById(R.id.confirm_heart);
        heartFilled = findViewById(R.id.confirm_heart_filled);
        heart.setClickable(true);
        heartFilled.setClickable(false);
        heartFilled.setVisibility(View.INVISIBLE);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.setClickable(false);
                heart.setVisibility(View.INVISIBLE);
                heartFilled.setClickable(true);
                heartFilled.setVisibility(View.VISIBLE);
            }
        });
        heartFilled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heart.setClickable(true);
                heart.setVisibility(View.VISIBLE);
                heartFilled.setClickable(false);
                heartFilled.setVisibility(View.INVISIBLE);
            }
        });



        Button btn = findViewById(R.id.confirm_continue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner time = findViewById(R.id.confirm_pick_up_time);
                Spinner method = findViewById(R.id.confirm_payment_method);
                String pickUpTime = time.getSelectedItem().toString();
                String paymentMethod = method.getSelectedItem().toString();
                Intent intent = new Intent(ConfirmOrder.this, MainActivity.class);
                Order order = new Order(selected, pickUpTime, paymentMethod, 0);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", order);
                intent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
