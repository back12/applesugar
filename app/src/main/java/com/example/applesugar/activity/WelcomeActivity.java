package com.example.applesugar.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.applesugar.R;
import com.example.applesugar.databinding.ActivityWelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;
    private NavController controller;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        NavHostFragment fragmentContainer = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.activity_welcome_fragment_container);
        controller = fragmentContainer.getNavController();

    }

    @Override
    public void onBackPressed() {
        if(controller.getPreviousBackStackEntry() != null) {
            controller.popBackStack();
            return;
        }
        super.onBackPressed();
    }

}
