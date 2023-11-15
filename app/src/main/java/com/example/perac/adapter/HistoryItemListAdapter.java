package com.example.perac.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.models.ChildDummyItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryItemListAdapter extends RecyclerView.Adapter<HistoryItemListAdapter.ViewHolder> {
     ArrayList<ChildDummyItem> childDummyItemArrayList;
     Activity activity;

     public HistoryItemListAdapter(ArrayList<ChildDummyItem> childDummyItemArrayList){

         this.childDummyItemArrayList = childDummyItemArrayList;
     }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_items_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
 ChildDummyItem childDummyItem = childDummyItemArrayList.get(position);
 holder.tvItemName.setText("Coffee Machiato");
 holder.tvItemQty.setText("x1");
    }

    @Override
    public int getItemCount() {
        return childDummyItemArrayList.size();
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