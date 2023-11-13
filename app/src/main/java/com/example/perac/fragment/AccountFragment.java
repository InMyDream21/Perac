package com.example.perac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.perac.R;
import com.example.perac.SignIn;
import com.example.perac.history_layout;
import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth mAuth;
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.hide();

        FrameLayout historyFL = rootView.findViewById(R.id.btn_history_acc);
        historyFL.setOnClickListener(this);

        Button signOut = rootView.findViewById(R.id.btn_sign_out_acc);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), SignIn.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_history_acc) {
            Intent hIntent = new Intent(requireActivity(), history_layout.class);
            startActivity(hIntent);
        }
    }
}
