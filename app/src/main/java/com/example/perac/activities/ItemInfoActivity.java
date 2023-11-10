package com.example.perac.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perac.R;
import com.example.perac.models.Menu;

public class ItemInfoActivity extends AppCompatActivity {
    public static String EXTRA_PERSON = "extra_person";
    private TextView tvJudul, tvHarga, tvRating, tvDeskripsi, tvBack, tvAdd, tvDecrease, tvCount;
    private ImageView imgMinuman;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);

        Menu mMenu = getIntent().getParcelableExtra(EXTRA_PERSON);
        tvJudul = findViewById(R.id.tv_judul);
        tvHarga = findViewById(R.id.tv_harga);
        tvRating = findViewById(R.id.tv_rating);
        tvDeskripsi = findViewById(R.id.tv_deskripsi);
        tvBack = findViewById(R.id.tv_back);
        tvAdd = findViewById(R.id.add_count);
        tvDecrease = findViewById(R.id.decrease_count);
        tvCount = findViewById(R.id.tv_count);
        imgMinuman = findViewById(R.id.image_minuman);

        tvJudul.setText(mMenu.getTitle());
        tvHarga.setText(mMenu.getPrice());
        tvRating.setText(mMenu.getRating());
        tvDeskripsi.setText(mMenu.getDetail());
        tvCount.setText(String.valueOf(count));
        imgMinuman.setImageResource(mMenu.getPhoto());
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                tvCount.setText(String.valueOf(count));
            }
        });

        tvDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count--;
                    tvCount.setText(String.valueOf(count));
                } else {
                    Toast.makeText(getApplicationContext(), "Cannot order less than 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}