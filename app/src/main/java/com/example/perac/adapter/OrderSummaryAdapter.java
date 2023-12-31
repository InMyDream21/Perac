package com.example.perac.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.perac.R;
import com.example.perac.activities.ItemInfoActivity;
import com.example.perac.fragment.HistoryFragment;
import com.example.perac.fragment.OrderSummaryFragment;
import com.example.perac.models.CartItem;
import com.example.perac.models.CartManager;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderSummaryAdapter extends RecyclerView.Adapter<OrderSummaryAdapter.ViewHolder> {

    private final List<CartItem> dataList;
    private CartListener cartListener;

    public void setCartListener(CartListener cartListener) {
        this.cartListener = cartListener;
    }

    private Context context;
    public OrderSummaryAdapter(Context context, List<CartItem> dataList) {
        this.context = context;
        this.dataList = dataList;
    }
    public interface CartListener{
        void onCartItemRemoved();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_summary_cards, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem item = dataList.get(position);
        Menu selectedMenu = MenuData.getDataByIndex(Integer.parseInt(item.getProductId()));


        Glide.with(holder.itemView.getContext())
                .load(selectedMenu.getPhoto())
                .apply(new RequestOptions().override(350, 250))
                .into(holder.ivOrderSummaryItem);

        holder.tvOrderSummaryItemName.setText(selectedMenu.getTitle());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = formatRupiah.format(selectedMenu.getPrice());
        holder.tvOrderSummaryItemPrice.setText(harga);
        String quantityText = "x" + item.getQuantity();
        holder.tvOrderSummaryTotalItems.setText(quantityText);
        holder.btnDeleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                deleteItem(adapterPosition);
                cartListener.onCartItemRemoved();
            }
        });

        holder.btnEditOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                CartItem item = dataList.get(adapterPosition);
                Menu menu = MenuData.getDataByIndex(Integer.parseInt(item.getProductId()));
                Intent intent = new Intent(context, ItemInfoActivity.class);
                intent.putExtra(ItemInfoActivity.EXTRA_PERSON, menu);
                context.startActivity(intent);
            }
        });
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

    private void deleteItem(int position) {
        CartManager.removeItem(position);
        notifyDataSetChanged();
    }
}