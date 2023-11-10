package com.example.perac.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.perac.R;
import com.example.perac.SignIn;
import com.example.perac.adapter.MenuListAdapter;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    private RecyclerView rvMenu;
    private ArrayList<Menu> list = new ArrayList<>();
    private String title = "Mode List";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), SignIn.class);
            startActivity(intent);
            finish();
        }

        rvMenu = findViewById(R.id.menu_list_item);
        rvMenu.setHasFixedSize(true);

        list.addAll(MenuData.getListData());
        showRecyclerGrid();
    }

    private void showRecyclerGrid(){
        rvMenu.setLayoutManager(new GridLayoutManager(this, 2));
        MenuListAdapter gridHeroAdapter = new MenuListAdapter(list);
        rvMenu.setAdapter(gridHeroAdapter);
    }
}