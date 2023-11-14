package com.example.perac.fragment;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perac.AccountActivity;
import com.example.perac.MainActivity;
import com.example.perac.R;
import com.example.perac.SignIn;
import com.example.perac.adapter.OrderSummaryAdapter;
import com.example.perac.models.CartItem;
import com.example.perac.models.CartManager;
import com.example.perac.models.Menu;
import com.example.perac.models.MenuData;
import com.example.perac.models.Order;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class OrderSummaryFragment extends Fragment implements View.OnClickListener {

    private List<CartItem> cartItems = CartManager.getCartItems();
    private RecyclerView recyclerView;
    private OrderSummaryAdapter adapter;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private TextView tvTotalHarga;
    public OrderSummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("orders");
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

        adapter = new OrderSummaryAdapter(cartItems, new OrderSummaryAdapter.CartListener() {
            @Override
            public void onCartItemRemoved() {
                onItemRemoved();
            }
        });
        recyclerView.setAdapter(adapter);

        tvTotalHarga = rootView.findViewById(R.id.tv_order_summary_totalPrice);
        updateTotalPrice();

            if (adapter.getItemCount() == 0){
                TextView noItemsYet = rootView.findViewById(R.id.tv_no_item_found);
                ImageView noItemImg = rootView.findViewById(R.id.img_no_items);
                LinearLayout llPrice = rootView.findViewById(R.id.ll_total_price);
                noItemsYet.setVisibility(View.VISIBLE);
                noItemImg.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                llPrice.setVisibility(View.GONE);

                Button btnCheckout = rootView.findViewById(R.id.btn_checkout_order_summary);
                btnCheckout.setVisibility(View.GONE);

            } else {

                Button btnCheckout = rootView.findViewById(R.id.btn_checkout_order_summary);
                btnCheckout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (cartItems.size() < 1) {
                            Toast.makeText(getActivity(), "Cart Masih Kosong", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        String currentDate = getCurrentDate();
                        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                        if (currentUser == null) {
                            Toast.makeText(getActivity(), "Silahkan Login Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), SignIn.class);
                            startActivity(intent);
                        }
                        String userId = currentUser.getUid();
                        saveOrderToDatabase(cartItems, currentDate, userId);
                    }
                });
                return rootView;
            }



        return rootView ;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_order_summary) {
            Intent backIntent = new Intent(requireActivity(), MainActivity.class);
            startActivity(backIntent);
        }
    }

    private int totalHarga(List<CartItem> items) {
        int harga = 0;
        for (CartItem item: items) {
            harga += item.getPrice() * item.getQuantity();
        }
        return harga;
    }

    private String getCurrentDate() {
        Date currentDate = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    private void saveOrderToDatabase(List<CartItem> cartItems, String currentDate, String userId) {
        Order order = new Order(currentDate, userId, cartItems);

        String orderId = databaseReference.push().getKey();
        databaseReference.child(orderId).setValue(order);
        CartManager.clearCart();
        Toast.makeText(getActivity(), "Checkout Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void onItemRemoved(){
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String harga = formatRupiah.format(totalHarga(CartManager.getCartItems()));
        tvTotalHarga.setText(harga);
    }
}