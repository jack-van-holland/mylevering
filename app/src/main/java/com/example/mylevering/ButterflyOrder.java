package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.util.ArrayList;

public class ButterflyOrder extends AppCompatActivity implements ButterflyMenuListAdapter.OnMenuItemListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ButterflyMenuOption> butterflyMenuList;
    private ButterflyMenuListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly_order);
        recyclerView = findViewById(R.id.menu_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Create list and add stuff to it
        butterflyMenuList = new ArrayList<ButterflyMenuOption>();
        ButterflyMenuOption tacoChicken = new ButterflyMenuOption("Chicken Taco", "$6.99", "350");
        butterflyMenuList.add(tacoChicken);
        ButterflyMenuOption tacoVeggie = new ButterflyMenuOption("Veggie Taco", "$5.99", "300");
        butterflyMenuList.add(tacoVeggie);
        ButterflyMenuOption tacoPork = new ButterflyMenuOption("Pork Taco", "$7.99", "400");
        butterflyMenuList.add(tacoPork);
        ButterflyMenuOption tortaChicken = new ButterflyMenuOption("Chicken Torta", "$6.99", "350");
        butterflyMenuList.add(tortaChicken);
        ButterflyMenuOption tortaVeggie = new ButterflyMenuOption("Veggie Torta", "$5.99", "300");
        butterflyMenuList.add(tortaVeggie);
        ButterflyMenuOption tortaPork = new ButterflyMenuOption("Pork Torta", "$7.99", "400");
        butterflyMenuList.add(tortaPork);

        adapter = new ButterflyMenuListAdapter(butterflyMenuList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void OnNoteClick(int position) {
        Intent intent = new Intent(this, ButterflyMenuItem.class);
        intent.putExtra("menu_item_selected", (Parcelable) butterflyMenuList.get(position));
        startActivity(intent);
    }
}
