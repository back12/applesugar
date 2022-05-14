package com.example.applesugar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

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
        NavHostFragment fragmentContainer = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController controller = fragmentContainer.getNavController();
        NavigationUI.setupWithNavController(binding.navBottom, controller);
    }
}