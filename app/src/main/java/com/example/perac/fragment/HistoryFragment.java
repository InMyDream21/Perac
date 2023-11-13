package com.example.perac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

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
            Intent backIntent = new Intent(requireActivity(), AccountActivity.class);
            startActivity(backIntent);
        }
    }
}
