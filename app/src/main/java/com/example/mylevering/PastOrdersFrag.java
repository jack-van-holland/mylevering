package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class PastOrdersFrag extends Fragment {

    private FirebaseDatabase dbase;
    private DatabaseReference dbref;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private List<Order> pOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbase = FirebaseDatabase.getInstance();
        dbref = dbase.getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        //for( dbref.child("users").child(user.getUid()).child("pOrders").
        //TODO(jack): create listview/recyclerview and populate with past orders from database
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("Past Orders");
        }

        return inflater.inflate(R.layout.fragment_past_orders, container, false);
    }
}