package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.concurrent.TimeUnit;

public class MyOrderFrag extends Fragment {
    private Order order;
    private ImageView heart;

    public static int UNSENT = 0;
    public static int SENT = 1;
    public static int IN_QUEUE = 2;
    public static int IN_PROGRESS = 3;
    public static int COMPLETED = 4;

    private boolean favorite;
    private boolean orderStatusStarted;
    private int currentStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity main = (MainActivity) getActivity();
        if (main != null) {
            main.setTitle("My Order");
        }
        favorite = false;
        return inflater.inflate(R.layout.fragment_my_order, container, false);
    }


    public void onStart() {
        super.onStart();
        heart = getActivity().findViewById(R.id.my_order_heart);
        ImageView typeImage = getActivity().findViewById(R.id.my_order_type_image);
        ImageView chefs = getActivity().findViewById(R.id.my_order_chefs);
        ImageView prog1 = getActivity().findViewById(R.id.my_order_prog1);
        ImageView prog2 = getActivity().findViewById(R.id.my_order_prog2);
        ImageView prog3 = getActivity().findViewById(R.id.my_order_prog3);
        ImageView prog4 = getActivity().findViewById(R.id.my_order_prog4);
        heart.setVisibility(View.INVISIBLE);
        typeImage.setVisibility(View.INVISIBLE);
        chefs.setVisibility(View.INVISIBLE);
        prog1.setVisibility(View.INVISIBLE);
        prog2.setVisibility(View.INVISIBLE);
        prog3.setVisibility(View.INVISIBLE);
        prog4.setVisibility(View.INVISIBLE);
        if (order != null) {
            favorite = order.isFavorite();
            String t = order.getTitle();
            TextView title = getActivity().findViewById(R.id.my_order_title);
            title.setText(t);
            String type = order.getType();
            TextView typeTv = getActivity().findViewById(R.id.my_order_type);
            typeTv.setText(type);
            heart.setVisibility(View.VISIBLE);
            typeImage.setVisibility(View.VISIBLE);
            chefs.setVisibility(View.VISIBLE);
            prog1.setVisibility(View.VISIBLE);
            prog2.setVisibility(View.VISIBLE);
            prog3.setVisibility(View.VISIBLE);
            prog4.setVisibility(View.VISIBLE);
            if (favorite) {
                heart.setImageResource(R.drawable.heart_filled);
            } else {
                heart.setImageResource(R.drawable.heart);
            }
            heart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (favorite) {
                        heart.setImageResource(R.drawable.heart);
                    } else {
                        heart.setImageResource(R.drawable.heart_filled);
                    }
                    favorite = !favorite;
                }
            });
            if (order.getType().equals("Fresh")) {
                typeImage.setImageResource(R.drawable.fresh);
            } else {
                typeImage.setImageResource(R.drawable.butterfly);
            }
            if (!orderStatusStarted) {
                orderStatusStarted = true;
                OrderStatus os = new OrderStatus(this);
                os.start();
            }
        }
    }

    public void onResume () {
        super.onResume();
        setStatus(currentStatus);
    }

    public void setStatusBackground(int status) {
        currentStatus = status;
    }


    public void setStatus(int status) {
        currentStatus = status;
        ImageView prog1 = getActivity().findViewById(R.id.my_order_prog1);
        ImageView prog2 = getActivity().findViewById(R.id.my_order_prog2);
        ImageView prog3 = getActivity().findViewById(R.id.my_order_prog3);
        ImageView prog4 = getActivity().findViewById(R.id.my_order_prog4);
        TextView progress = getActivity().findViewById(R.id.my_order_status);
        if (status == UNSENT) { // has not left the phone yet
            prog1.setImageResource(R.drawable.progbar_incomplete);
            prog2.setImageResource(R.drawable.progbar_incomplete);
            prog3.setImageResource(R.drawable.progbar_incomplete);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.unsent);
        } else if (status == SENT) { // sent to the restaurant
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar_incomplete);
            prog3.setImageResource(R.drawable.progbar_incomplete);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.sent);
        } else if (status == IN_QUEUE) { // accepted by the restaurant
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar2);
            prog3.setImageResource(R.drawable.progbar_incomplete);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.in_queue);
        } else if (status == IN_PROGRESS) {
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar2);
            prog3.setImageResource(R.drawable.progbar3);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.in_progress);
        } else if (status == COMPLETED) {
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar2);
            prog3.setImageResource(R.drawable.progbar3);
            prog4.setImageResource(R.drawable.progbar4);
            progress.setText(R.string.complete);
        }
    }

    public void submitOrder(Order ord) {
        order = ord;
    }
}