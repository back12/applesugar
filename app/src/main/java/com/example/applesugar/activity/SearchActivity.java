package com.example.applesugar.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applesugar.adapter.TopMovieRecyclerAdapter;
import com.example.applesugar.databinding.ActivitySearchBinding;
import com.example.applesugar.utils.RecyclerViewItemDecoration;
import com.example.applesugar.viewmodel.HomeViewModel;


public class SearchActivity extends AppCompatActivity {
    private ActivitySearchBinding binding;
    private HomeViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        model = new ViewModelProvider(this).get(HomeViewModel.class);

        binding.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                String name = binding.etSearch.getText().toString();
                model.getMovieLikeName(name).observe(this, topMovies -> {
                    binding.tvHint.setVisibility(View.INVISIBLE);
                    if(topMovies.size() == 0){
                        binding.tvHint.setVisibility(View.VISIBLE);
                    }
                    binding.rv.setAdapter(new TopMovieRecyclerAdapter(topMovies, true));
                });

            }
            return false;
        });
        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.rv.addItemDecoration(new RecyclerViewItemDecoration(this, 20, 0, 0));
        binding.ivBack.setOnClickListener(v -> finish());

        binding.etSearch.requestFocus();
        InputMethodManager inputManager =(InputMethodManager)binding.etSearch.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputManager.showSoftInput(binding.etSearch, 0);
            }
        }, 100);
    }
}
