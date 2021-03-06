package com.example.mylevering;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        List<String> chickenRestrictions = new ArrayList<>();
        chickenRestrictions.add("Chicken");
        chickenRestrictions.add("Dairy");
        chickenRestrictions.add("Gluten");
        List<String> vegRestrictions = new ArrayList<>();
        vegRestrictions.add("Gluten");
        vegRestrictions.add("Dairy");
        List<String> porkRestrictions = new ArrayList<>();
        porkRestrictions.add("Pork");
        porkRestrictions.add("Dairy");
        porkRestrictions.add("Gluten");
        // Create list and add stuff to it
        butterflyMenuList = new ArrayList<ButterflyMenuOption>();
        ButterflyMenuOption tacoChicken = new ButterflyMenuOption("Chicken Taco", "$6.99", "350",
                "Corn tortilla, marinated pulled chicken, black beans, shredded mozzarella, cilantro, lime juice, pico de gallo\n", chickenRestrictions, true);
        butterflyMenuList.add(tacoChicken);
        ButterflyMenuOption tacoVeggie = new ButterflyMenuOption("Veggie Taco", "$5.99", "300",
                "Flour tortilla, bell peppers, black beans, shredded mozzarella, cilantro, lime juice, pico de gallo\n", vegRestrictions, true);
        butterflyMenuList.add(tacoVeggie);
        ButterflyMenuOption tacoPork = new ButterflyMenuOption("Pork Taco", "$7.99", "400",
                "Flour tortilla, marinated pulled pork, black beans, shredded mozzarella, cilantro, lime juice, pico de gallo\n", porkRestrictions, true);
        butterflyMenuList.add(tacoPork);
        ButterflyMenuOption tortaChicken = new ButterflyMenuOption("Chicken Torta", "$6.99", "350",
                "Bread, seasoned pulled chicken, black beans, avocado, pico de gallo\n", chickenRestrictions, false);
        butterflyMenuList.add(tortaChicken);
        ButterflyMenuOption tortaVeggie = new ButterflyMenuOption("Veggie Torta", "$5.99", "300",
                "Bread, seasoned bell peppers, black beans, avocado, pico de gallo\n", vegRestrictions, true);
        butterflyMenuList.add(tortaVeggie);
        ButterflyMenuOption tortaPork = new ButterflyMenuOption("Pork Torta", "$7.99", "400",
                "Bread, marinated pulled pork, black beans, avocado, pico de gallo\n", porkRestrictions, true);
        butterflyMenuList.add(tortaPork);

        adapter = new ButterflyMenuListAdapter(butterflyMenuList, this, getApplicationContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void OnNoteClick(int position) {
        ButterflyMenuOption bmo = butterflyMenuList.get(position);
        if(bmo.isAvailable()) {
            Intent intent = new Intent(this, ButterflyMenuItem.class);
            //intent.putExtra("menu_item_selected", (Parcelable) butterflyMenuList.get(position));
            Bundle bundle = new Bundle();
            bundle.putSerializable("selected", bmo);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
