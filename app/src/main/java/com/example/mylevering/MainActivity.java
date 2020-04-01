package com.example.mylevering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Fragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        frag = new KitchensFrag();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, frag).commit();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.my_order) {
            frag = new MyOrderFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();
        }
        else if (id == R.id.kitchens) {
            frag = new KitchensFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();
        }
        else if (id == R.id.past_orders) {
            frag = new PastOrdersFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();
        }
        else if (id == R.id.payment) {
            frag = new PaymentFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();
        }
        else if (id == R.id.settings) {
            frag = new SettingsFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, frag).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
