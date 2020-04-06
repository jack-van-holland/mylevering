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
    ButterflyMenuListAdapter(ArrayList<ButterflyMenuOption> butterflyMenuList) {
        this.butterflyMenuList = butterflyMenuList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.butterfly_menu_item,parent,false);
        //LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(
          //      parent.getContext()).inflate(R.layout.butterfly_menu_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ButterflyMenuOption menuItem = butterflyMenuList.get(position);
        holder.title.setText(menuItem.getTitle());
        holder.price.setText(menuItem.getPrice());
        holder.calories.setText(menuItem.getCalories());
    }

    @Override
    public int getItemCount() {
        return butterflyMenuList.size();
    }

    /*
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
     */

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        TextView calories;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.menu_item_title);
            price = itemView.findViewById(R.id.menu_item_price);
            calories = itemView.findViewById(R.id.menu_item_calories);
        }
    }
}
