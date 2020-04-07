package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        ImageView heart = getActivity().findViewById(R.id.my_order_heart);
        ImageView typeImage = getActivity().findViewById(R.id.my_order_type_image);
        heart.setVisibility(View.INVISIBLE);
        typeImage.setVisibility(View.INVISIBLE);
        super.onStart();
        if (order != null) {
            String t = order.getTitle();
            TextView title = getActivity().findViewById(R.id.my_order_title);
            title.setText(t);
            String type = order.getType();
            TextView typeTv = getActivity().findViewById(R.id.my_order_type);
            typeTv.setText(type);
            heart.setVisibility(View.VISIBLE);
            typeImage.setVisibility(View.VISIBLE);
            if (order.isFavorite()) {
                heart.setImageResource(R.drawable.heart_filled);
            } else {
                heart.setImageResource(R.drawable.heart);
            }
            if (order.getType().equals("Fresh")) {
                typeImage.setImageResource(R.drawable.fresh);
            } else {
                typeImage.setImageResource(R.drawable.butterfly);
            }

        }
    }


    public void submitOrder(Order ord) {
        order = ord;
    }
}