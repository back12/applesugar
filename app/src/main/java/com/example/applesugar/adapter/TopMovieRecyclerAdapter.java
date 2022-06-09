package com.example.applesugar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applesugar.activity.TopMovieDetailActivity;
import com.example.applesugar.databinding.ItemHomeMovieTopBinding;
import com.example.applesugar.db.entity.TopMovie;

import java.io.Serializable;
import java.util.List;

public class TopMovieRecyclerAdapter extends RecyclerView.Adapter {
    private List<TopMovie> list;
    private boolean showAll = false;

    public TopMovieRecyclerAdapter(List<TopMovie> list, boolean showAll) {
        this.list = list;
        this.showAll = showAll;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeMovieTopBinding binding = ItemHomeMovieTopBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TopMovie item = list.get(position);
        ((ItemViewHolder) holder).serial.setText(String.valueOf(position + 1));
        ((ItemViewHolder) holder).title.setText(item.getName());
        ((ItemViewHolder) holder).rating.setText(item.getRating());
        ((ItemViewHolder) holder).genre.setText(item.getGenre());
        ((ItemViewHolder) holder).date.setText(item.getReleaseDate());
        ((ItemViewHolder) holder).description.setText(item.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(item.getImg())
                .into(((ItemViewHolder) holder).cover);

        Context context = holder.itemView.getContext();
        Intent intent = new Intent(context, TopMovieDetailActivity.class)
                .putExtra("position", position)
                .putExtra("list", (Serializable) list);
        ((ItemViewHolder) holder).item.setOnClickListener(v -> context.startActivity(intent));
    }

    @Override
    public int getItemCount() {
        return showAll ? list.size() : 5;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView serial, title, rating, genre, date, description;
        private ImageView cover;
        private ConstraintLayout item;

        public ItemViewHolder(@NonNull ItemHomeMovieTopBinding binding) {
            super(binding.getRoot());
            serial = binding.tvSerial;
            title = binding.tvName;
            rating = binding.tvRating;
            genre = binding.tvGenre;
            date = binding.tvDate;
            description = binding.tvDescription;
            cover = binding.ivCover;
            cover.setClipToOutline(true);
            item = binding.item;
        }
    }
}
