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

import java.util.List;

public class ButterflyMenuListAdapter extends RecyclerView.Adapter<ButterflyMenuListAdapter.MyViewHolder> /*ArrayAdapter<ButterflyMenuOption>*/ {

    private List<ButterflyMenuOption> ButterflyMenuList;

    public ButterflyMenuListAdapter(List<ButterflyMenuOption> ButterflyMenuList) {
        this.ButterflyMenuList = ButterflyMenuList;
    }

    /*
    private int resource;
    public ButterflyMenuListAdapter(Context ctx, int res, List<ButterflyMenuOption> items) {
        super(ctx, res, items);
        resource = res;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout menuListView;
        ButterflyMenuOption bm = getItem(position);

        if (convertView == null) {
            menuListView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, menuListView, true);
        } else {
            menuListView = (LinearLayout) convertView;
        }
        TextView titleView = (TextView) menuListView.findViewById(R.id.);
        TextView priceView = (TextView) menuListView.findViewById(R.id.);
        TextView caloriesView = (TextView) menuListView.findViewById(R.id.);

        titleView.setText(bm.getTitle());
        priceView.setText(bm.getWhen());
        caloriesView.setText(bm.getPaid()==1 ? "PAID" : "unpaid");

        return menuListView;

    } */

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LinearLayout linearLayout = LayoutInflater.from(parent).inflate(R.layout.butterfly_menu_item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout butterflyMenuItem;
        public MyViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);
            butterflyMenuItem = itemView;
        }
    }
}
