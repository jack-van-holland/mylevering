package com.example.mylevering;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ButterflyMenuListAdapter extends RecyclerView.Adapter<ButterflyMenuListAdapter.MyViewHolder> {

    private ArrayList<ButterflyMenuOption> butterflyMenuList;
    private OnMenuItemListener mOnMenuItemListener;
    ButterflyMenuListAdapter(ArrayList<ButterflyMenuOption> butterflyMenuList, OnMenuItemListener onMenuItemListener) {
        this.butterflyMenuList = butterflyMenuList;
        this.mOnMenuItemListener = onMenuItemListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.butterfly_menu_item,parent,false);
        return new MyViewHolder(view, mOnMenuItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ButterflyMenuOption menuItem = butterflyMenuList.get(position);
        String cal = menuItem.getCalories() + " Calories";
        holder.title.setText(menuItem.getTitle());
        holder.price.setText(menuItem.getPrice());
        holder.calories.setText(cal);
    }

    @Override
    public int getItemCount() {
        return butterflyMenuList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView price;
        TextView calories;
        OnMenuItemListener onMenuItemListener;

        private MyViewHolder(@NonNull View itemView, OnMenuItemListener onMenuItemListener) {
            super(itemView);
            title = itemView.findViewById(R.id.menu_item_title);
            price = itemView.findViewById(R.id.menu_item_price);
            calories = itemView.findViewById(R.id.menu_item_calories);


            this.onMenuItemListener = onMenuItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMenuItemListener.OnNoteClick(getAdapterPosition());
        }
    }

    public interface OnMenuItemListener {
        void OnNoteClick(int position);
    }

}
