package com.example.perac.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.perac.AccountActivity;
import com.example.perac.MainActivity;
import com.example.perac.R;
import com.example.perac.adapter.OrderSummaryAdapter;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;

import java.util.ArrayList;


public class OrderSummaryFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Menu> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private OrderSummaryAdapter adapter;
    public OrderSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order_summary, container, false);
        FrameLayout flback = rootView.findViewById(R.id.btn_back_order_summary);
        flback.setOnClickListener(this);



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

        recyclerView = rootView.findViewById(R.id.rv_order_summary);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new OrderSummaryAdapter(list);
        recyclerView.setAdapter(adapter);
        list.addAll(MenuData.getListData());
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_order_summary) {
            Intent backIntent = new Intent(requireActivity(), MainActivity.class);
            startActivity(backIntent);
        }
    }
}