package com.example.perac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.fragment.HistoryFragment;
import com.example.perac.models.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
     private ArrayList<Order> dataList;
     private HistoryFragment historyFragment;

    public HistoryAdapter(HistoryFragment activity, ArrayList<Order> dataList) {
        this.historyFragment = activity;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cards,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = dataList.get(position);
        holder.tvItemID.setText(order.orderId);
        holder.tvItemDate.setText(order.date);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = formatRupiah.format(Integer.parseInt(order.total));
        holder.tvItemTotal.setText(harga);

        HistoryItemListAdapter historyItemListAdapter = new HistoryItemListAdapter(order.cartItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(historyFragment.getContext());
        holder.nested_rv.setLayoutManager(linearLayoutManager);
        holder.nested_rv.setAdapter(historyItemListAdapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView nested_rv;
        TextView tvItemID,tvItemDate,tvItemTotal;

         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             tvItemID = itemView.findViewById(R.id.tv_order_id);
             tvItemDate = itemView.findViewById(R.id.tv_order_date_id);
             tvItemTotal = itemView.findViewById(R.id.tv_order_total_id);
             nested_rv = itemView.findViewById(R.id.rv_order_item);
         }
     }
}