package com.example.applesugar.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applesugar.databinding.ItemHomeMovieBinding;

public class HomeMovieRecyclerAdapter extends RecyclerView.Adapter {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeMovieBinding binding = ItemHomeMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;

        public MovieViewHolder(@NonNull ItemHomeMovieBinding binding) {
            super(binding.getRoot());
            cover = binding.ivCover;
            cover.setClipToOutline(true);
        }
    }
}
