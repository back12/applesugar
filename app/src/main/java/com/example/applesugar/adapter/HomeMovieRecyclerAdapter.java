package com.example.applesugar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applesugar.R;
import com.example.applesugar.databinding.ItemHomeMovieBinding;
import com.example.applesugar.db.entity.OnScreenMovie;

import org.w3c.dom.Text;

import java.util.List;

import coil.ImageLoader;
import coil.request.ImageRequest;

public class HomeMovieRecyclerAdapter extends RecyclerView.Adapter {
    private List<OnScreenMovie> list;

    public HomeMovieRecyclerAdapter(List<OnScreenMovie> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeMovieBinding binding = ItemHomeMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Glide.with(context)
                .load(list.get(position).getImg())
                .into(((MovieViewHolder) holder).ivCover);
        ((MovieViewHolder) holder).tvScore.setText(list.get(position).getRating());
    }

    @Override
    public int getItemCount() {
        return list.size() / 2;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCover;
        private TextView tvScore;

        public MovieViewHolder(@NonNull ItemHomeMovieBinding binding) {
            super(binding.getRoot());
            ivCover = binding.ivCover;
            ivCover.setClipToOutline(true);
            tvScore = binding.tvScore;
        }
    }
}
