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

import com.example.perac.R;
import com.example.perac.AccountActivity;

public class HistoryFragment extends Fragment implements View.OnClickListener {

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
