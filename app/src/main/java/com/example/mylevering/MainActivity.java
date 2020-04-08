package com.example.mylevering;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Fragment kitchenFrag;
    private Fragment myOrderFrag;
    private Fragment pastOrdersFrag;
    private Fragment paymentFrag;
    private Fragment settingsFrag;
    private FragmentTransaction transaction;


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

        kitchenFrag = new KitchensFrag();
        myOrderFrag = new MyOrderFrag();
        pastOrdersFrag = new PastOrdersFrag();
        paymentFrag = new PaymentFrag();
        settingsFrag = new SettingsFrag();

        Intent intent = getIntent();
        int flag = intent.getFlags();
        if (flag == Intent.FLAG_ACTIVITY_TASK_ON_HOME) {
            Bundle bundle = intent.getExtras();
            Order order = (Order) bundle.getSerializable("order");
            ((MyOrderFrag) myOrderFrag).submitOrder(order);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, myOrderFrag).commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, kitchenFrag).commit();
        }

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.my_order) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, myOrderFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if (id == R.id.kitchens) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, kitchenFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if (id == R.id.past_orders) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, pastOrdersFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if (id == R.id.payment) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, paymentFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }
        else if (id == R.id.settings) {
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, settingsFrag);
            transaction.addToBackStack(null);
            transaction.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
