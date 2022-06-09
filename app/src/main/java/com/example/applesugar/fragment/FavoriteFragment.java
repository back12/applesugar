package com.example.applesugar.fragment;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applesugar.R;
import com.example.applesugar.adapter.FavoriteRecyclerAdapter;
import com.example.applesugar.databinding.FragmentFavoriteBinding;
import com.example.applesugar.db.entity.MarkedMovie;
import com.example.applesugar.utils.RecyclerViewItemDecoration;
import com.example.applesugar.utils.ScreenUtil;
import com.example.applesugar.viewmodel.MarkedMovieViewModel;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private FragmentFavoriteBinding binding;
    private MarkedMovieViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(MarkedMovieViewModel.class);

        FavoriteRecyclerAdapter adapter = new FavoriteRecyclerAdapter();

        adapter.setOnChipsCheckListener((group, checkedIds) -> {
            if(group.getChildAt(0).getId() == checkedIds.get(0)){
                model.getMarkedMovieByType("0").observe(getViewLifecycleOwner(), markedMovies -> {
                    adapter.setList(markedMovies);
                    adapter.setType(0);
                    adapter.notifyDataSetChanged();
                });
            }else if(group.getChildAt(1).getId() == checkedIds.get(0)){
                model.getMarkedMovieByType("1").observe(getViewLifecycleOwner(), markedMovies -> {
                    adapter.setList(markedMovies);
                    adapter.setType(1);
                    adapter.notifyDataSetChanged();
                });
            }
        });
        model.getMarkedMovieByType("0").observe(getViewLifecycleOwner(), markedMovies -> {
            adapter.setList(markedMovies);
            adapter.notifyItemRangeInserted(0, markedMovies.size());
        });
        binding.rv.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(position == 0){
                    return 2;
                }else{
                    return 1;
                }
            }
        });
        binding.rv.setLayoutManager(layoutManager);
        binding.rv.addItemDecoration(new RecyclerViewItemDecoration(getContext(), 40, 20, 1));
    }

//    private void drawVertical(Canvas canvas, RecyclerView parent) {
//        canvas.save();
//        final int left;
//        final int right;
//        final Rect mBounds = new Rect();
//        final Drawable mDivider = ContextCompat.getDrawable(getContext(), R.drawable.divider);
//        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
//        if (parent.getClipToPadding()) {
//            left = parent.getPaddingLeft();
//            right = parent.getWidth() - parent.getPaddingRight();
//            canvas.clipRect(left, parent.getPaddingTop(), right,
//                    parent.getHeight() - parent.getPaddingBottom());
//        } else {
//            left = 0;
//            right = parent.getWidth();
//        }
//
//        final int childCount = parent.getChildCount();
//        for (int i = 1; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            parent.getDecoratedBoundsWithMargins(child, mBounds);
//            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
//            final int top = bottom - mDivider.getIntrinsicHeight();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(canvas);
//        }
//        canvas.restore();
//    }
}
