package com.example.mylevering;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PaymentMethodListAdapter extends RecyclerView.Adapter<PaymentMethodListAdapter.PaymentViewHolder> {

    private ArrayList<PaymentMethod> paymentMethodList;
    private Context context;

    PaymentMethodListAdapter(ArrayList<PaymentMethod> paymentMethodList, Context context) {
        this.paymentMethodList = paymentMethodList;
        this.context = context;
    }

    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.payment_method_item, parent,false);
        return new PaymentViewHolder(view);
    }

    static class PaymentViewHolder extends RecyclerView.ViewHolder {
        TextView paymentName;
        TextView payBalance;
        TextView mealPlan;

        private PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            paymentName = itemView.findViewById(R.id.payment_item_title);
            payBalance = itemView.findViewById(R.id.payment_item_info1);
            mealPlan = itemView.findViewById(R.id.payment_item_info2);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder holder, int position) {
        PaymentMethod payMethod = paymentMethodList.get(position);
        holder.paymentName.setText(payMethod.getCardName());
        holder.payBalance.setText(payMethod.getBalance());
        holder.mealPlan.setText(payMethod.getMealPlan());
    }

    @Override
    public int getItemCount() {
        return paymentMethodList.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
