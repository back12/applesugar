package com.example.applesugar.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applesugar.databinding.ItemHomeActorBinding;

public class HomeActorRecyclerAdapter extends RecyclerView.Adapter {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeActorBinding binding = ItemHomeActorBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ActorViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ActorViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;

        public ActorViewHolder(@NonNull ItemHomeActorBinding binding) {
            super(binding.getRoot());
            cover = binding.ivActor;
            cover.setClipToOutline(true);
        }
    }
}
