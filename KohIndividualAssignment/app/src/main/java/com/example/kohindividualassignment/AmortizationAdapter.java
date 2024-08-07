package com.example.kohindividualassignment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AmortizationAdapter extends RecyclerView.Adapter<AmortizationAdapter.AmortizationViewHolder> {
    private List<AmortizationItem> amortizationItemList;

    public AmortizationAdapter(List<AmortizationItem> amortizationItemList) {
        this.amortizationItemList = amortizationItemList;
    }

    @NonNull
    @Override
    public AmortizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_amortization, parent, false);
        return new AmortizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AmortizationViewHolder holder, int position) {
        AmortizationItem item = amortizationItemList.get(position);
        holder.textViewPaymentNumber.setText(String.valueOf(item.getPaymentNumber()));
        holder.textViewBeginningBalance.setText(String.format("%.2f", item.getBeginningBalance()));
        holder.textViewMonthlyRepayment.setText(String.format("%.2f", item.getMonthlyRepayment()));
        holder.textViewInterestPaid.setText(String.format("%.2f", item.getInterestPaid()));
        holder.textViewPrincipalPaid.setText(String.format("%.2f", item.getPrincipalPaid()));
    }

    @Override
    public int getItemCount() {
        return amortizationItemList.size();
    }

    public static class AmortizationViewHolder extends RecyclerView.ViewHolder {
        TextView textViewPaymentNumber, textViewBeginningBalance, textViewMonthlyRepayment, textViewInterestPaid, textViewPrincipalPaid;

        public AmortizationViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPaymentNumber = itemView.findViewById(R.id.textViewPaymentNumber);
            textViewBeginningBalance = itemView.findViewById(R.id.textViewBeginningBalance);
            textViewMonthlyRepayment = itemView.findViewById(R.id.textViewMonthlyRepayment);
            textViewInterestPaid = itemView.findViewById(R.id.textViewInterestPaid);
            textViewPrincipalPaid = itemView.findViewById(R.id.textViewPrincipalPaid);
        }
    }
}