package com.example.perac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.perac.databinding.ActivityMainBinding;
import com.example.perac.fragment.AccountFragment;
import com.example.perac.fragment.HistoryFragment;
import com.example.perac.fragment.HomepageFragment;
import com.example.perac.fragment.MenuListFragment;
import com.example.perac.fragment.OrderSummaryFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setBackground(null);

        replaceFragment(new HomepageFragment());
        
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.fr_home) {
                replaceFragment(new HomepageFragment());
            } else if (item.getItemId() == R.id.fr_menu) {
                 replaceFragment(new MenuListFragment());
            } else if (item.getItemId() == R.id.fr_cart) {
                 replaceFragment(new OrderSummaryFragment());
            } else if (item.getItemId() == R.id.fr_account) {
                 replaceFragment(new AccountFragment());
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
