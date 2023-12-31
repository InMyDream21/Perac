package com.example.perac.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perac.R;
import com.example.perac.adapter.OrderSummaryAdapter;
import com.example.perac.fragment.OrderSummaryFragment;
import com.example.perac.models.CartManager;
import com.example.perac.models.Menu;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ItemInfoActivity extends AppCompatActivity {
    public static String EXTRA_PERSON = "extra_person";
    private TextView tvJudul, tvHarga, tvDeskripsi, tvBack, tvAdd, tvDecrease, tvCount, tvKalori, tvTotal;
    private ImageView imgMinuman;
    private Button addToCart;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main)));
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.actionbar_top);
            actionBar.hide();
        }

        Menu mMenu = getIntent().getParcelableExtra(EXTRA_PERSON);

        tvJudul = findViewById(R.id.tv_judul);
        tvJudul.setText(mMenu.getTitle());

        tvHarga = findViewById(R.id.tv_harga);
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = formatRupiah.format(mMenu.getPrice());
        tvHarga.setText(harga);

        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvDeskripsi.setText(mMenu.getDetail());

        tvKalori = findViewById(R.id.tv_kalori);
        tvKalori.setText(mMenu.getCalorie());

        tvTotal = findViewById(R.id.tv_total);
        tvTotal.setText(formatRupiah.format(mMenu.getPrice()));

        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(String.valueOf(count));

        imgMinuman = findViewById(R.id.image_minuman);
        imgMinuman.setImageResource(mMenu.getPhoto());

        tvBack = findViewById(R.id.tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAdd = findViewById(R.id.add_count);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvCount.setText(String.valueOf(count));
                updateTotal(mMenu.getPrice());
            }
        });

        tvDecrease = findViewById(R.id.decrease_count);
        tvDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count--;
                    tvCount.setText(String.valueOf(count));
                    updateTotal(mMenu.getPrice());
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot order less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addToCart = findViewById(R.id.btn_add_to_cart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartManager.addToCart(mMenu, count);
                Toast.makeText(getApplicationContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
                OrderSummaryAdapter orderSummaryAdapter = new OrderSummaryAdapter(getApplicationContext(), CartManager.getCartItems());
                orderSummaryAdapter.notifyDataSetChanged();
                finish();
            }
        });
    }

    private void updateTotal(int harga) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        tvTotal.setText(formatRupiah.format((long) harga * count));
    }
}