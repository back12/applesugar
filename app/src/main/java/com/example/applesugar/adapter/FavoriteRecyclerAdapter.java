package com.example.applesugar.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applesugar.R;
import com.example.applesugar.databinding.ItemFavoriteBinding;
import com.example.applesugar.databinding.ItemFavoriteTopBinding;
import com.example.applesugar.db.entity.MarkedMovie;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class FavoriteRecyclerAdapter extends RecyclerView.Adapter {
    private final static String[] categories = new String[]{"想看", "看过"};
    private OnChipsCheckListener onChipsCheckListener;
    private List<MarkedMovie> list;
    private int type;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            ItemFavoriteTopBinding binding = ItemFavoriteTopBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new TopViewHolder(binding);
        } else {
            ItemFavoriteBinding binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ItemViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 1 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public interface OnChipsCheckListener{
        void onCheckChanged(ChipGroup group, List<Integer> checkedIds);
    }

    public void setOnChipsCheckListener(OnChipsCheckListener onChipsCheckListener) {
        this.onChipsCheckListener = onChipsCheckListener;
    }


    public void setList(List<MarkedMovie> list) {
        this.list = list;
    }

    public void setType(int type) {
        this.type = type;
    }

    class TopViewHolder extends RecyclerView.ViewHolder {
        private ChipGroup cgCategory;

        public TopViewHolder(@NonNull ItemFavoriteTopBinding binding) {
            super(binding.getRoot());
            cgCategory = binding.cgCategory;
            for (String category : categories) {
                Chip chip = (Chip) ((Activity) cgCategory.getContext()).getLayoutInflater().inflate(R.layout.chip_choice, cgCategory, false);
                chip.setText(category);
                chip.setId(ViewCompat.generateViewId());
                cgCategory.addView(chip);
            }
            cgCategory.check(cgCategory.getChildAt(0).getId());
            cgCategory.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
                @Override
                public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {
                    onChipsCheckListener.onCheckChanged(group, checkedIds);
                }
            });
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView cover;

        public ItemViewHolder(@NonNull ItemFavoriteBinding binding) {
            super(binding.getRoot());
            cover = binding.ivCover;
            cover.setClipToOutline(true);
        }
    }
}
