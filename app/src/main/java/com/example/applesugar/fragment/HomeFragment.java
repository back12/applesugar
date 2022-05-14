package com.example.applesugar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;

import com.example.applesugar.adapter.HomeActorRecyclerAdapter;
import com.example.applesugar.adapter.HomeMovieRecyclerAdapter;
import com.example.applesugar.databinding.FragmentHomeBinding;
import com.example.applesugar.util.RecyclerViewItemDecoration;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rvMovie.setAdapter(new HomeMovieRecyclerAdapter());
        binding.rvMovie.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvMovie.addItemDecoration(new RecyclerViewItemDecoration(getContext(), 30, -15, 0));

        binding.rvActor.setAdapter(new HomeActorRecyclerAdapter());
        binding.rvActor.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rvActor.addItemDecoration(new RecyclerViewItemDecoration(getContext(), 40, 10, 0));
    }
}
