package com.example.mylevering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ButterflyMenuListAdapter extends RecyclerView.Adapter<ButterflyMenuListAdapter.MyViewHolder> {

    private ArrayList<ButterflyMenuOption> butterflyMenuList;

    public ButterflyMenuListAdapter(ArrayList<ButterflyMenuOption> butterflyMenuList) {
        this.butterflyMenuList = butterflyMenuList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.butterfly_menu_item,parent,false);
        //LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(
          //      parent.getContext()).inflate(R.layout.butterfly_menu_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setMenuItemDetails(butterflyMenuList.get(position));
    }

    @Override
    public int getItemCount() {
        return butterflyMenuList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //LinearLayout butterflyMenuItem;
        TextView title;
        TextView price;
        TextView calories;

        private void setMenuItemDetails(ButterflyMenuOption menuOption) {
            title.setText(menuOption.getTitle());
            price.setText(menuOption.getPrice());
            calories.setText(menuOption.getCalories());
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //butterflyMenuItem = itemView.findViewById(R.id.menu_item);
            title = itemView.findViewById(R.id.menu_item_title);
            price = itemView.findViewById(R.id.menu_item_price);
            calories = itemView.findViewById(R.id.menu_item_calories);

        }
    }
}
