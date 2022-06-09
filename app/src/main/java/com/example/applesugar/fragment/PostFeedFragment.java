package com.example.applesugar.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.example.applesugar.R;
import com.example.applesugar.adapter.PostFeedRecyclerAdapter;
import com.example.applesugar.databinding.FragmentRvBinding;
import com.example.applesugar.db.entity.Post;
import com.example.applesugar.utils.RecyclerViewItemDecoration;
import com.example.applesugar.utils.ScreenUtil;
import com.example.applesugar.viewmodel.PostViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PostFeedFragment extends Fragment {
    private FragmentRvBinding binding;
    private PostFeedRecyclerAdapter adapter;
    private FloatingActionButton mFloatingActionButton;
    private PostViewModel model;
    private int size = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRvBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        model = new ViewModelProvider(this).get(PostViewModel.class);

        model.getPostList().observe(getViewLifecycleOwner(), new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                if(posts.size() == size){
                    adapter.setPosts(posts);
                    return;
                }
                adapter = new PostFeedRecyclerAdapter(posts);
                adapter.setOnLikeClickListener((liked, pid) -> {
                    model.updateLiked(1 - liked, pid).observe(getViewLifecycleOwner(), integer -> {
                        adapter.notifyItemChanged(posts.size() - pid);
                    });
                });
                binding.rv.setAdapter(adapter);
                size = posts.size();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rv.setLayoutManager(layoutManager);
        binding.rv.addItemDecoration(new RecyclerViewItemDecoration(getContext(), 20, 20, 0) {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int position = parent.getChildAdapterPosition(view);
                if (position == state.getItemCount() - 1) {
                    outRect.bottom = ScreenUtil.dip2px(getContext(), 150);
                    outRect.top = ScreenUtil.dip2px(getContext(), 25);
                }
            }
        });
        mFloatingActionButton = requireActivity().findViewById(R.id.fab);
        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });
    }
}
