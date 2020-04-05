package com.example.mylevering;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class KitchensFrag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("Kitchens");
        }
        View currView = inflater.inflate(R.layout.fragment_kitchens, container, false);

        ImageView freshButton = (ImageView) currView.findViewById(R.id.fresh);
        freshButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FreshOrder.class);
                startActivity(intent);
            }
        });

        ImageView butterflyButton = (ImageView) currView.findViewById(R.id.butterfly);
        butterflyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ButterflyOrder.class);
                startActivity(intent);
            }
        });

        return currView;
    }
}
