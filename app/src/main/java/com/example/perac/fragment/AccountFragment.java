package com.example.perac.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.perac.R;
import com.example.perac.SignIn;
import com.example.perac.history_layout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private FirebaseAuth mAuth;
    public AccountFragment() {
        // Required empty public constructor
    }

    DatabaseReference databaseReference;
    TextView tvAccName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        ActionBar actionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        actionBar.hide();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        tvAccName = rootView.findViewById(R.id.tv_acc_name);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(getActivity(), SignIn.class);
            startActivity(intent);
        }

        String userId = currentUser.getUid();
        setAccountName(userId);

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
            replaceFragment(new HistoryFragment());
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    private void setAccountName(String userId) {
        Query userQuery = databaseReference.orderByKey().equalTo(userId);
        userQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot userSnapshot: snapshot.getChildren()) {
                    String username = userSnapshot.child("username").getValue(String.class);

                    tvAccName.setText("Hello, " + username);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error reading data: " + error.getMessage());
            }
        });
    }
}
