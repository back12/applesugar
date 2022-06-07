package com.example.applesugar.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applesugar.databinding.ItemPostBinding;
import com.example.applesugar.db.entity.Post;

import java.util.List;

public class PostFeedRecyclerAdapter extends RecyclerView.Adapter {
    private List<Post> posts;
    private OnLikeClickListener onLikeClickListener;

    public PostFeedRecyclerAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post item = posts.get(position);
        ((ItemViewHolder) holder).name.setText(item.getUsername());
        ((ItemViewHolder) holder).content.setText(item.getContent());
        ((ItemViewHolder) holder).like.setSelected(item.getLiked() == 1);
        ((ItemViewHolder) holder).like.setOnClickListener(v -> {
            onLikeClickListener.onClick(item.getLiked(), item.getPid());
        });
    }

    public void setOnLikeClickListener(OnLikeClickListener onLikeClickListener) {
        this.onLikeClickListener = onLikeClickListener;
    }

    public interface OnLikeClickListener{
        void onClick(int liked, int pid);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, content;
        ImageView like;

        public ItemViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            name = binding.tvName;
            content = binding.tvContent;
            like = binding.ivLike;
        }
    }
}
