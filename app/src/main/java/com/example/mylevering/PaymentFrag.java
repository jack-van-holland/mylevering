package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentFrag extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<PaymentMethod> paymentMethodList;
    private PaymentMethodListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("Payment");
            main.setContentView(R.layout.fragment_payment);
            recyclerView = main.findViewById(R.id.payment_list);
            layoutManager = new LinearLayoutManager(main);
        }
        recyclerView.setLayoutManager(layoutManager);

        paymentMethodList = new ArrayList<PaymentMethod>();
        PaymentMethod jCard = new PaymentMethod("JCard", "$275", "14 Meals Per Week");

        //return inflater.inflate(R.layout.fragment_payment, container, false);
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }
}