package com.example.perac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.AccountActivity;
import com.example.perac.adapter.HistoryAdapter;
import com.example.perac.models.ChildDummyItem;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;
import com.example.perac.models.Order;
import com.example.perac.models.ParentDummyItem;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements View.OnClickListener {
     RecyclerView rvHistoryParent;
     HistoryAdapter historyAdapter;
    ArrayList<ParentDummyItem> parentDummyItemArrayList;
    ArrayList<ChildDummyItem> childDummyItemArrayList;
    private ArrayList<Menu> orderItem = MenuData.getListData();
    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_history, container, false);

        FrameLayout flback = rootView.findViewById(R.id.btn_back_history);
        flback.setOnClickListener(this);

        rvHistoryParent = rootView.findViewById(R.id.rv_history);

        String[] orderIDParent = {"0918230", "1230987" , "1236919"};
        String[] dateParent = {"29/12/23", "12/12/22", "11/11/21"};
        int[] totalPriceParent = {150000,20000,30000};
        int[] qtyItemChild = {1,3,5};
        String[] itemNameChild = {"Coffe Machiato", "Espresso" , "Gula Aren"};

        parentDummyItemArrayList = new ArrayList<>();
        childDummyItemArrayList = new ArrayList<>();

        for (int i = 0; i < orderIDParent.length; i++) {
            ParentDummyItem parentDummyItem = new ParentDummyItem(orderIDParent[i], dateParent[i], totalPriceParent[i]);
            parentDummyItemArrayList.add(parentDummyItem);
                ChildDummyItem childDummyItem = new ChildDummyItem(itemNameChild[i], qtyItemChild[i]);
                childDummyItemArrayList.add(childDummyItem);

        }




        historyAdapter = new HistoryAdapter(this,parentDummyItemArrayList,childDummyItemArrayList);

        rvHistoryParent.setAdapter(historyAdapter);
        rvHistoryParent.setLayoutManager(new LinearLayoutManager(requireActivity()));

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_history) {
            replaceFragment(new AccountFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
