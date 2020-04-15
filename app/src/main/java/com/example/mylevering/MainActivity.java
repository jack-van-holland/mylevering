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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView;
    private Fragment kitchenFrag;
    private Fragment myOrderFrag;
    private Fragment pastOrdersFrag;
    private Fragment paymentFrag;
    private Fragment settingsFrag;
    private FragmentTransaction transaction;
    private SharedPreferences sp;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

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

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();

        Intent intent = getIntent();
        int flag = intent.getFlags();
        if (flag == Intent.FLAG_ACTIVITY_TASK_ON_HOME) {
            Bundle bundle = intent.getExtras();
            Order order = (Order) bundle.getSerializable("order");
            ((MyOrderFrag) myOrderFrag).submitOrder(order);
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, myOrderFrag);
            transaction.addToBackStack(null);
            transaction.commit();
            menu.findItem(R.id.my_order).setVisible(true);
            sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor pe = sp.edit();
            pe.putBoolean("orderable", false);
            pe.commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, kitchenFrag).commit();
            menu.findItem(R.id.my_order).setVisible(false);
            sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor pe = sp.edit();
            pe.putBoolean("orderable", true);
            pe.commit();
        }

        //TODO: populate nav drawer name with user's name
        //TODO: make MainActivity the landing page once user logs in
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
        else if (id == R.id.logout) {
            //TODO: add "are you sure you want to log out" box
            auth.signOut();
            Intent intent = new Intent(MainActivity.this, WelcomeLanding.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void returnToKitchen() {
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, kitchenFrag);
        transaction.addToBackStack(null);
        transaction.commit();
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.my_order).setVisible(false);
        menu.findItem(R.id.my_order).setVisible(false);
        sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor pe = sp.edit();
        pe.putBoolean("orderable", true);
        pe.commit();
    }
}
