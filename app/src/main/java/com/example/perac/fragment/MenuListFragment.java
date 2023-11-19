package com.example.perac.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.activities.ItemInfoActivity;
import com.example.perac.adapter.MenuListAdapter;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import java.util.ArrayList;

public class MenuListFragment extends Fragment {
    private RecyclerView rvMenu;
    private ArrayList<Menu> list = new ArrayList<>();
    private String title = "Mode List";
    private SearchView svMenu;
    private MenuListAdapter menuListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu_list, container, false);



        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.main)));
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.actionbar_top);

            TextView actionBarTitle = actionBar.getCustomView().findViewById(R.id.action_bar_title);
            if (actionBarTitle != null) {
                actionBarTitle.setText("Menu List");
            }
        }

        rvMenu = rootView.findViewById(R.id.menu_list_item);
        rvMenu.setHasFixedSize(true);
        svMenu = rootView.findViewById(R.id.sv_menulist);
        svMenu.clearFocus();
        svMenu.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        list.addAll(MenuData.getListData());
        showRecyclerGrid();

        return rootView;
    }


    private void showRecyclerGrid() {
        rvMenu.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        menuListAdapter = new MenuListAdapter(list);
        rvMenu.setAdapter(menuListAdapter);

        menuListAdapter.setOnItemClickCallback(new MenuListAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Menu menu) {
                goToItemInfo(menu);
            }
        });
    }

    private void goToItemInfo(Menu menu) {
        Menu mMenu = new Menu();
        mMenu.setId(menu.getId());
        mMenu.setTitle(menu.getTitle());
        mMenu.setCalorie(menu.getCalorie());
        mMenu.setDetail(menu.getDetail());
        mMenu.setPhoto(menu.getPhoto());
        mMenu.setPrice(menu.getPrice());
        mMenu.setRating(menu.getRating() + "/5.0");

        Intent intent = new Intent(getActivity(), ItemInfoActivity.class);
        intent.putExtra(ItemInfoActivity.EXTRA_PERSON, mMenu);
        startActivity(intent);
    }

    private long lastToastTime = 0;
    private static final long TOAST_DELAY = 5000;

    private void filterList(String newText) {
        ArrayList<Menu> filteredList = new ArrayList<>();
        for (Menu i : list){
            if (i.getTitle().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(i);
            }
        }

        long currentTime = System.currentTimeMillis();

        if (filteredList.isEmpty()) {
            if (currentTime - lastToastTime > TOAST_DELAY) {
                Toast.makeText(requireContext(), "No data Found", Toast.LENGTH_SHORT).show();
                lastToastTime = currentTime;
            }
        } else {
            menuListAdapter.setFilteredList(filteredList);
        }

    }



}


