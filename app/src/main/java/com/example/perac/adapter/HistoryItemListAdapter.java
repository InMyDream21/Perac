package com.example.perac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.models.Menu;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class HistoryItemListAdapter extends RecyclerView.Adapter<HistoryItemListAdapter.HistoryItemListViewHolder> {
    private ArrayList<Menu> orderItems;

    public HistoryItemListAdapter(ArrayList<Menu> orderItemText) {
        this.orderItems = orderItemText;
    }

    public void setOrderItemText(ArrayList<Menu> orderItemText) {
        this.orderItems = orderItemText;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryItemListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_list, parent, false);
        return new HistoryItemListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemListViewHolder holder, int position) {

        holder.tvOrderItemName.setText("x1 Coffee Machiato");
        // Add other bindings as needed
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public  class HistoryItemListViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOrderItemName; // Add other views as needed

        public HistoryItemListViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrderItemName = itemView.findViewById(R.id.tv_orderlist_items); // Replace with the actual ID
        }
    }
}