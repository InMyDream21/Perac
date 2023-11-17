package com.example.perac.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perac.R;
import com.example.perac.adapter.HistoryAdapter;
import com.example.perac.models.CartItem;
import com.example.perac.models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment implements View.OnClickListener {
     RecyclerView rvHistoryParent;
     HistoryAdapter historyAdapter;
     private DatabaseReference databaseReference;
     private FirebaseAuth firebaseAuth;
     private ArrayList<Order> dataList = new ArrayList<>();
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

        databaseReference = FirebaseDatabase.getInstance().getReference().child("orders");
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            getOrdersByUserId(userId);
        }

        rvHistoryParent.setLayoutManager(new LinearLayoutManager(requireActivity()));
        historyAdapter = new HistoryAdapter(this, dataList);
        rvHistoryParent.setAdapter(historyAdapter);

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

    private void getOrdersByUserId(String userId){
         Query userOrdersRef = databaseReference.orderByChild("userId").equalTo(userId);
         userOrdersRef.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for (DataSnapshot orderSnapshot: snapshot.getChildren()) {
                     String orderId = orderSnapshot.getKey();
                     String date = orderSnapshot.child("date").getValue(String.class);
                     String total = orderSnapshot.child("total").getValue(String.class);
                     DataSnapshot cartItemsSnapshot = orderSnapshot.child("cartItems");
                     List<CartItem> items = new ArrayList<>();

                     for (DataSnapshot itemSnapshot : cartItemsSnapshot.getChildren()) {
                         String productId = itemSnapshot.child("productId").getValue(String.class);
                         int quantity = itemSnapshot.child("quantity").getValue(Integer.class);

                         CartItem item = new CartItem(productId, quantity);
                         items.add(item);
                     }
                     Order currentOrder = new Order(date, userId, items, total);
                     currentOrder.setOrderId(orderId);
                     dataList.add(currentOrder);
                     historyAdapter.notifyDataSetChanged();
                 }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error reading data: " + error.getMessage());
             }
         });
    }
}
