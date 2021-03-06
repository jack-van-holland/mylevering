package com.example.mylevering;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pl.droidsonroids.gif.GifImageView;


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

    private FirebaseDatabase dbase;
    private DatabaseReference dbref;
    private FirebaseAuth auth;
    private FirebaseUser user;

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

        dbase = FirebaseDatabase.getInstance();
        dbref = dbase.getReference();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        heart = getActivity().findViewById(R.id.my_order_heart);
        ImageView typeImage = getActivity().findViewById(R.id.my_order_type_image);
        ImageView chefs = getActivity().findViewById(R.id.my_order_image);
        ImageView prog1 = getActivity().findViewById(R.id.my_order_prog1);
        ImageView prog2 = getActivity().findViewById(R.id.my_order_prog2);
        ImageView prog3 = getActivity().findViewById(R.id.my_order_prog3);
        ImageView prog4 = getActivity().findViewById(R.id.my_order_prog4);
        TextView status = getActivity().findViewById(R.id.my_order_status);
        Button completeOrder = getActivity().findViewById(R.id.my_order_complete);

        heart.setVisibility(View.INVISIBLE);
        typeImage.setVisibility(View.INVISIBLE);
        chefs.setVisibility(View.INVISIBLE);
        prog1.setVisibility(View.INVISIBLE);
        prog2.setVisibility(View.INVISIBLE);
        prog3.setVisibility(View.INVISIBLE);
        prog4.setVisibility(View.INVISIBLE);
        status.setVisibility(View.INVISIBLE);
        completeOrder.setVisibility(View.INVISIBLE);

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
        completeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order.setFavorite(favorite);
                order.setID(String.format("o-%04d", ((MainActivity)getActivity()).pOrders.size() + 1));
                // Send order to database of past orders for current user
                dbref.child("users").child(user.getUid()).child("pOrders")
                        .child(order.getId()).setValue(order);

                favorite = false;
                orderStatusStarted = false;
                currentStatus = UNSENT;
                ((MainActivity) getActivity()).returnToKitchen();
                order = null;
            }
        });

        if (order != null) {
            favorite = order.isFavorite();
            String t;
            if (order.getButterflyMenu() != null) {
                t = order.getButterflyMenu().getTitle();
            } else {
                t = order.getFreshMenu().getTitle();
            }
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
            status.setVisibility(View.VISIBLE);
            if (favorite) {
                heart.setImageResource(R.drawable.heart_filled);
            } else {
                heart.setImageResource(R.drawable.heart);
            }

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
        GifImageView im = getActivity().findViewById(R.id.my_order_image);
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
            im.setImageResource(R.drawable.mailsending);
        } else if (status == SENT) { // sent to the restaurant
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar_incomplete);
            prog3.setImageResource(R.drawable.progbar_incomplete);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.sent);
            im.setImageResource(R.drawable.chef2);
        } else if (status == IN_QUEUE) { // accepted by the restaurant
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar2);
            prog3.setImageResource(R.drawable.progbar_incomplete);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.in_queue);
            im.setImageResource(R.drawable.chef2);
        } else if (status == IN_PROGRESS) {
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar2);
            prog3.setImageResource(R.drawable.progbar3);
            prog4.setImageResource(R.drawable.progbar_incomplete);
            progress.setText(R.string.in_progress);
            im.setImageResource(R.drawable.chef1);
        } else if (status == COMPLETED) {
            prog1.setImageResource(R.drawable.progbar1);
            prog2.setImageResource(R.drawable.progbar2);
            prog3.setImageResource(R.drawable.progbar3);
            prog4.setImageResource(R.drawable.progbar4);
            progress.setText(R.string.complete);
            im.setImageResource(R.drawable.ready_for_pickup);
            Button completeOrder = getActivity().findViewById(R.id.my_order_complete);
            completeOrder.setVisibility(View.VISIBLE);
        }
    }

    public void submitOrder(Order ord) {
        order = ord;
    }
}