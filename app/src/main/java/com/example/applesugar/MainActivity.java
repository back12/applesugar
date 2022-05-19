package com.example.applesugar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.applesugar.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        NavHostFragment fragmentContainer = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController controller = fragmentContainer.getNavController();
        NavigationUI.setupWithNavController(binding.navBottom, controller);
        binding.navBottom.setOnItemReselectedListener(item -> {
            //禁止重复创建fragment
        });
    }
}