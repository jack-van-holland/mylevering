package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MyOrderFrag extends Fragment {
    private Order order;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("My Order");
        }
        return inflater.inflate(R.layout.fragment_my_order, container, false);
    }


    public void onStart() {
        super.onStart();
        if (order != null) {
            String title = order.getTitle();
            TextView tv = getActivity().findViewById(R.id.my_order_title);
            tv.setText(title);
        }
    }


    public void submitOrder(Order ord) {
        order = ord;
    }
}