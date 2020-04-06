package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class ConfirmOrder extends AppCompatActivity {

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
        MenuOption selected = (MenuOption) bundle.getSerializable("selected");
        String from = bundle.getString("from");
        Log.d("description", selected.getDescription());
        Log.d("title", selected.getTitle());
        TextView desc = findViewById(R.id.confirm_description);
        TextView title = findViewById(R.id.confirm_title);
        TextView price = findViewById(R.id.confirm_price);
        TextView type = findViewById(R.id.confirm_category);
        desc.setText(Html.fromHtml(selected.getDescription()));
        title.setText(selected.getTitle());
        price.setText(selected.getPrice());
        type.setText(from);

        Button btn = findViewById(R.id.confirm_continue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmOrder.this, MainActivity.class);
                intent.addFlags(0);
                startActivity(intent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
