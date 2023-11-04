package com.example.perac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class history_layout extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_layout);

        FrameLayout flback = findViewById(R.id.btn_back_history);
        flback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_history){
            Intent backIntent = new Intent(history_layout.this, AccountActivity.class);
            startActivity(backIntent);
        }
    }
}