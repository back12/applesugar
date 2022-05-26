package com.example.applesugar.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Window;

import com.example.applesugar.R;
import com.example.applesugar.databinding.ActivityMainBinding;

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
        NavHostFragment fragmentContainer = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_fragment_container);
        NavController controller = fragmentContainer.getNavController();
        NavigationUI.setupWithNavController(binding.navBottom, controller);
        binding.navBottom.setOnItemReselectedListener(item -> {
            //禁止重复创建fragment
        });
    }
}