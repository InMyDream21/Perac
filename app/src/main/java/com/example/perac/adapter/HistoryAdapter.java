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
import com.example.perac.models.ChildDummyItem;
import com.example.perac.models.ParentDummyItem;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
     ArrayList<ParentDummyItem> parentDummyItemArrayList;
     ArrayList<ChildDummyItem> childDummyItemArrayList;
     private HistoryFragment historyFragment;

    public HistoryAdapter(HistoryFragment activity, ArrayList<ParentDummyItem> parentDummyItemArrayList, ArrayList<ChildDummyItem> childDummyItemArrayList) {
        this.historyFragment = activity;
        this.parentDummyItemArrayList = parentDummyItemArrayList;
        this.childDummyItemArrayList = childDummyItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_cards,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ParentDummyItem parentDummyItem = parentDummyItemArrayList.get(position);
   holder.tvItemID.setText(parentDummyItem.orderIDParent);
   holder.tvItemDate.setText(parentDummyItem.orderDateParent);
   holder.tvItemTotal.setText(String.valueOf(parentDummyItem.orderTotalPriceParent));




   HistoryItemListAdapter historyItemListAdapter = new HistoryItemListAdapter(childDummyItemArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(historyFragment.getContext());
        holder.nested_rv.setLayoutManager(linearLayoutManager);
        holder.nested_rv.setAdapter(historyItemListAdapter);
    }

    @Override
    public int getItemCount() {
        return parentDummyItemArrayList.size();
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