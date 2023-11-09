package com.example.perac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        FrameLayout historyFL = findViewById(R.id.btn_history_acc);

        historyFL.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_history_acc){
            Intent hIntent = new Intent(AccountActivity.this, history_layout.class);
            startActivity(hIntent);

        }
    }


}