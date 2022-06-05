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
import com.example.applesugar.activity.OnScreenMovieDetailActivity;
import com.example.applesugar.databinding.ItemHomeMovieBinding;
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
        ItemHomeMovieBinding binding = ItemHomeMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Glide.with(context)
                .load(list.get(position).getImg())
                .into(((ItemViewHolder) holder).ivCover);
        ((ItemViewHolder) holder).tvScore.setText(list.get(position).getRating());
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
//                ((MovieViewHolder) holder).ivCover, "movie");
        Intent intent = new Intent(context, OnScreenMovieDetailActivity.class)
                .putExtra("position", position)
                .putExtra("list", (Serializable) list);

        ((ItemViewHolder) holder).ivCover.setOnClickListener(v -> context.startActivity(intent));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCover;
        private TextView tvScore;

        public ItemViewHolder(@NonNull ItemHomeMovieBinding binding) {
            super(binding.getRoot());
            ivCover = binding.ivCover;
            ivCover.setClipToOutline(true);
            tvScore = binding.tvScore;
        }
    }
}
