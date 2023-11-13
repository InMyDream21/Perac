package com.example.perac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.activities.ItemInfoActivity;
import com.example.perac.adapter.MenuListAdapter;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import java.util.ArrayList;

public class HomepageFragment extends Fragment {

    private RecyclerView rvMenu;
    private ArrayList<Menu> list = new ArrayList<>();
    private String title = "Mode List";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_homepage, container, false);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.hide();

        rvMenu = rootView.findViewById(R.id.menu_list_item);
        rvMenu.setHasFixedSize(true);

        list.addAll(MenuData.getListData());
        showRecyclerGrid();

        return rootView;
    }

    private void showRecyclerGrid(){
        rvMenu.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        MenuListAdapter menuListAdapter = new MenuListAdapter(list);
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
}
