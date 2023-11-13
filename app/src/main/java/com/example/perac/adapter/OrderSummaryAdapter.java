package com.example.perac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.perac.R;
import com.example.perac.models.Menu;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private final ArrayList<Menu> dataList;
    private OnItemClickCallback onItemClickCallback;

    public OrderSummaryAdapter(ArrayList<Menu> dataList) {
        this.dataList = dataList;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_summary_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menu menu = dataList.get(position);

        // Glide for image loading
        Glide.with(holder.itemView.getContext())
                .load(menu.getPhoto())
                .apply(new RequestOptions().override(350, 250))
                .into(holder.ivOrderSummaryItem);

        holder.tvOrderSummaryItemName.setText(menu.getTitle());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = formatRupiah.format(menu.getPrice());
        holder.tvOrderSummaryItemPrice.setText(harga);
        holder.tvOrderSummaryTotalItems.setText("x1");


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivOrderSummaryItem;
        TextView tvOrderSummaryItemName, tvOrderSummaryItemPrice, tvOrderSummaryTotalItems;
        Button btnEditOrder, btnDeleteOrder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivOrderSummaryItem = itemView.findViewById(R.id.iv_order_summary_item);
            tvOrderSummaryItemName = itemView.findViewById(R.id.tv_order_summary_itemName);
            tvOrderSummaryItemPrice = itemView.findViewById(R.id.tv_order_summary_itemPrice);
            tvOrderSummaryTotalItems = itemView.findViewById(R.id.tv_order_summary_totalItems);
            btnEditOrder = itemView.findViewById(R.id.btn_edit_order);
            btnDeleteOrder = itemView.findViewById(R.id.btn_delete_order);
        }
    }

    public interface OnItemClickCallback {
        void onEditClicked(Menu menu);

        void onDeleteClicked(Menu menu);
    }
}