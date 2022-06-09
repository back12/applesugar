package com.example.applesugar.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applesugar.databinding.ItemCommentBinding;
import com.example.applesugar.databinding.ItemPostBinding;
import com.example.applesugar.db.entity.Comment;
import com.example.applesugar.db.entity.Post;

import java.util.List;
import java.util.Locale;

public class CommentFeedRecyclerAdapter extends RecyclerView.Adapter {
    private List<Comment> comments;
    private OnLikeClickListener onLikeClickListener;

    public CommentFeedRecyclerAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCommentBinding binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comment item = comments.get(position);
        ((ItemViewHolder) holder).name.setText(item.getUsername());
        ((ItemViewHolder) holder).content.setText(item.getContent());
        ((ItemViewHolder) holder).rating.setText(String.format(Locale.CHINA, "评分：%d分", item.getRating()));
    }

    public void setOnLikeClickListener(OnLikeClickListener onLikeClickListener) {
        this.onLikeClickListener = onLikeClickListener;
    }

    public interface OnLikeClickListener {
        void onClick(int liked, int pid);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name, content, rating;

        public ItemViewHolder(@NonNull ItemCommentBinding binding) {
            super(binding.getRoot());
            name = binding.tvName;
            content = binding.tvContent;
            rating = binding.tvRating;
        }
    }
}
