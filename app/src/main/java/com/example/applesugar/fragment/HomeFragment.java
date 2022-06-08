package com.example.applesugar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.applesugar.activity.SearchActivity;
import com.example.applesugar.adapter.TopMovieRecyclerAdapter;
import com.example.applesugar.adapter.OnScreenMovieRecyclerAdapter;
import com.example.applesugar.databinding.FragmentHomeBinding;
import com.example.applesugar.db.entity.TopMovie;
import com.example.applesugar.utils.RecyclerViewItemDecoration;
import com.example.applesugar.viewmodel.HomeViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private HomeViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(this).get(HomeViewModel.class);

        model.getOnScreenMovieList().observe(getViewLifecycleOwner(), onScreenMovies -> {
            binding.rvMovie.setAdapter(new OnScreenMovieRecyclerAdapter(onScreenMovies));
        });
        binding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvMovie.addItemDecoration(new RecyclerViewItemDecoration(getContext(), 30, -10, 0));

        model.getTopMovieList().observe(getViewLifecycleOwner(), onScreenMovies -> {
            binding.rvMovieTop.setAdapter(new TopMovieRecyclerAdapter(onScreenMovies, false));
        });
        binding.rvMovieTop.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvMovieTop.addItemDecoration(new RecyclerViewItemDecoration(getContext(), 20, 20, 0));

        binding.ivSearch.setOnClickListener(v -> requireContext().startActivity(new Intent(requireContext(), SearchActivity.class)));
    }
}
