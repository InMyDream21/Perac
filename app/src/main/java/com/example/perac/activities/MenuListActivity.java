package com.example.perac.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.perac.R;
import com.example.perac.adapter.MenuListAdapter;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import java.util.ArrayList;

public class MenuListActivity extends AppCompatActivity {
    private RecyclerView rvMenu;
    private ArrayList<Menu> list = new ArrayList<>();
    private String title = "Mode List";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);

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