package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ButterflyOrder extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ButterflyMenuOption> butterflyMenuList;

    //
    private ButterflyMenuListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterfly_order);

        recyclerView = findViewById(R.id.menu_list);
        layoutManager = new LinearLayoutManager(this);

        //list
        butterflyMenuList = new ArrayList<ButterflyMenuOption>();
        ButterflyMenuOption menuItem1 = new ButterflyMenuOption("Title1", "Price1", "250 Calories");
        butterflyMenuList.add(menuItem1);
        ButterflyMenuOption menuItem2 = new ButterflyMenuOption("Title2", "Price2", "250 Calories");
        butterflyMenuList.add(menuItem2);
        //list
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ButterflyMenuListAdapter(butterflyMenuList);
        recyclerView.setHasFixedSize(true);
    }
}
