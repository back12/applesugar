package com.example.applesugar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applesugar.activity.OnScreenMovieActivity;
import com.example.applesugar.databinding.ItemHomeMovieBinding;
import com.example.applesugar.databinding.ItemOnScreenMovieBinding;
import com.example.applesugar.db.entity.OnScreenMovie;

import java.io.Serializable;
import java.util.List;


public class OnScreenMovieRecyclerAdapter extends RecyclerView.Adapter {
    private List<OnScreenMovie> list;

    public OnScreenMovieRecyclerAdapter(List<OnScreenMovie> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOnScreenMovieBinding binding = ItemOnScreenMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Glide.with(context)
                .load(list.get(position).getImg())
                .into(((MovieViewHolder) holder).ivCover);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCover;

        public MovieViewHolder(@NonNull ItemOnScreenMovieBinding binding) {
            super(binding.getRoot());
            ivCover = binding.ivCover;
            ivCover.setClipToOutline(true);
        }
    }
}
