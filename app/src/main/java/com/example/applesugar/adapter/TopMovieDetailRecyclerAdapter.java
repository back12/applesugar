package com.example.applesugar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applesugar.databinding.ItemOnScreenMovieBinding;
import com.example.applesugar.db.entity.TopMovie;

import java.util.List;


public class TopMovieDetailRecyclerAdapter extends RecyclerView.Adapter {
    private List<TopMovie> list;
    private OnItemClickListener onItemClickListener;

    public TopMovieDetailRecyclerAdapter(List<TopMovie> list) {
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
        ((MovieViewHolder) holder).ivCover.setOnClickListener(v -> {
            onItemClickListener.onClick(position);
        });
    }

    public interface OnItemClickListener{
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
