package com.example.perac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.models.CartItem;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<Menu> orderList;

private RecyclerView HistoryItemRecyclerView;
    public HistoryAdapter(ArrayList<Menu> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cards, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
Menu order = orderList.get(position);
        holder.orderIdTextView.setText("02382211");
holder.totalTextView.setText("Rp150.000");
holder.dateTextView.setText("28/08/23");
//ArrayList<Menu> cartItems = order.getListData();
//holder.historyItemListAdapter.setOrderItemText(cartItems);
//holder.historyItemListAdapter.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private TextView orderIdTextView;
        private TextView dateTextView;
        private TextView totalTextView;
        private  TextView tvOrderListItems;
        private RecyclerView HistoryItemRecyclerView;
        private HistoryItemListAdapter historyItemListAdapter;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            orderIdTextView = itemView.findViewById(R.id.tv_order_id);
            dateTextView = itemView.findViewById(R.id.tv_order_date_id);
            totalTextView = itemView.findViewById(R.id.tv_order_total_id);

             tvOrderListItems = itemView.findViewById(R.id.tv_orderlist_items);

            HistoryItemRecyclerView = itemView.findViewById(R.id.rv_order_item);
            historyItemListAdapter = new HistoryItemListAdapter(new ArrayList<>());
            HistoryItemRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            HistoryItemRecyclerView.setAdapter(historyItemListAdapter);


        }



        private String calculateTotal(List<CartItem> cartItems) {
            int total = 0;
            for (CartItem cartItem : cartItems) {
                total += cartItem.getPrice() * cartItem.getQuantity();
            }
            return String.valueOf(total);
        }


    }


}