package com.example.mylevering;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class ButterflyMenuListAdapter extends RecyclerView.Adapter<ButterflyMenuListAdapter.MyViewHolder> {

    private ArrayList<ButterflyMenuOption> butterflyMenuList;
    private OnMenuItemListener mOnMenuItemListener;
    private Context context;

    ButterflyMenuListAdapter(ArrayList<ButterflyMenuOption> butterflyMenuList, OnMenuItemListener onMenuItemListener,
                             Context context) {
        this.butterflyMenuList = butterflyMenuList;
        this.mOnMenuItemListener = onMenuItemListener;
        this.context = context;
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
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        ButterflyMenuOption menuItem = butterflyMenuList.get(position);
        String cal = menuItem.getCalories() + " Calories";
        holder.title.setText(menuItem.getTitle());
        holder.price.setText(menuItem.getPrice());
        holder.calories.setText(cal);


        if (!menuItem.isAvailable()) {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.colorGray));
            holder.warningText.setText(R.string.out_of_stock);
        }else if (sp.contains("settings_dietary_restrictions")) {
            // do stuff for warnings
            Set<String> restrictions = sp.getStringSet("settings_dietary_restrictions",
                    Collections.<String>emptySet());
            if (restrictions.contains("Kosher") && !menuItem.isKosher()) {
                warn(holder, R.string.warning_kosher);
            } else if (restrictions.contains("Halal") && !menuItem.isHalal()) {
                warn(holder, R.string.warning_halal);
            } else if (restrictions.contains("Nut Allergy") && menuItem.isNutAllergy()) {
                warn(holder, R.string.warning_nut);
            } else if (restrictions.contains("Shellfish Allergy") && menuItem.isShellfishAllergy() ) {
                warn(holder, R.string.warning_shellfish);
            } else if (restrictions.contains("Dairy-Free") && !menuItem.isDairyFree()) {
                warn(holder, R.string.warning_dairy);
            } else if (restrictions.contains("Gluten-Free") && !menuItem.isGlutenFree()) {
                warn(holder, R.string.warning_gluten);
            } else if (restrictions.contains("Vegan") && !menuItem.isVegan()) {
                warn(holder, R.string.warning_vegan);
            } else if (restrictions.contains("Vegetarian") && !menuItem.isVegetarian()) {
                warn(holder, R.string.warning_vegetarian);
            }

        }

    }

    private void warn(@NonNull MyViewHolder holder, int message) {
        holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.warningBackground));
        holder.title.setTextColor(holder.itemView.getResources().getColor(R.color.warningForeground));
        holder.price.setTextColor(holder.itemView.getResources().getColor(R.color.warningForeground));
        holder.calories.setTextColor(holder.itemView.getResources().getColor(R.color.warningForeground));
        holder.warningText.setTextColor(holder.itemView.getResources().getColor(R.color.warningForeground));
        holder.warningText.setText(message);
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
        TextView warningText;
        OnMenuItemListener onMenuItemListener;

        private MyViewHolder(@NonNull View itemView, OnMenuItemListener onMenuItemListener) {
            super(itemView);
            title = itemView.findViewById(R.id.menu_item_title);
            price = itemView.findViewById(R.id.menu_item_price);
            calories = itemView.findViewById(R.id.menu_item_calories);
            warningText = itemView.findViewById(R.id.warning_text);


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
