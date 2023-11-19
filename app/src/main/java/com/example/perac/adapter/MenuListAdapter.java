package com.example.perac.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.perac.R;
import com.example.perac.models.Menu;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.GridViewHolder>{
    private ArrayList<Menu> listMenu;

    private OnItemClickCallback onItemClickCallback;
    public MenuListAdapter(ArrayList<Menu> list) {
        this.listMenu = list;
    }
    public void setFilteredList(ArrayList<Menu> filteredList){
        this.listMenu = filteredList;
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public MenuListAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_menu, parent, false);
        return new MenuListAdapter.GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdapter.GridViewHolder holder, int position) {
        Menu menu = listMenu.get(position);
        Glide.with(holder.itemView.getContext())
                .load(menu.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.menuTitle.setText(menu.getTitle());
        holder.menuCal.setText(menu.getCalorie());
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = formatRupiah.format(menu.getPrice());
        holder.menuPrice.setText(harga);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMenu.get(holder.getAdapterPosition()));
            }
        });
    }

    public interface OnItemClickCallback {
        void onItemClicked(Menu data);
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView menuTitle, menuCal, menuPrice;
        public GridViewHolder(@NonNull View itemView){
            super(itemView);


            imgPhoto = itemView.findViewById(R.id.menu_image);
            menuTitle = itemView.findViewById(R.id.menu_title);
            menuCal = itemView.findViewById(R.id.menu_cal);
            menuPrice = itemView.findViewById(R.id.menu_price);
        }
    }
}
