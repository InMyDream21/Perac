package com.example.perac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.perac.activities.HomepageActivity;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        Button toMain = findViewById(R.id.btn_sign_in);

        toMain.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sign_in){
            Intent goMain = new Intent(SignIn.this, HomepageActivity.class);
            startActivity(goMain);
        }
    }
}