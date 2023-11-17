package com.example.perac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.models.CartItem;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import java.util.List;

public class HistoryItemListAdapter extends RecyclerView.Adapter<HistoryItemListAdapter.ViewHolder> {
     List<CartItem> datalist;


     public HistoryItemListAdapter(List<CartItem> datalist){
         this.datalist = datalist;
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem data = datalist.get(position);
        Menu menu = MenuData.getDataByIndex(Integer.parseInt(data.getProductId()));
        holder.tvItemName.setText(menu.getTitle());
        holder.tvItemQty.setText("x" + data.getQuantity());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemName, tvItemQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tv_orderlist_items);
            tvItemQty = itemView.findViewById(R.id.tv_orderlist_qty);
        }
    }


}