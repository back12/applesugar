package com.example.applesugar.activity;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applesugar.adapter.OnScreenMovieDetailRecyclerAdapter;
import com.example.applesugar.databinding.ActivityOnScreenMovieBinding;
import com.example.applesugar.db.entity.MarkedMovie;
import com.example.applesugar.db.entity.OnScreenMovie;
import com.example.applesugar.utils.RecyclerViewItemDecoration;
import com.example.applesugar.viewmodel.MarkedMovieViewModel;

import java.util.List;

public class OnScreenMovieDetailActivity extends AppCompatActivity {
    private ActivityOnScreenMovieBinding binding;
    private MarkedMovieViewModel model;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnScreenMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(MarkedMovieViewModel.class);

        int position = getIntent().getIntExtra("position", -1);
        List<OnScreenMovie> list = (List<OnScreenMovie>) getIntent().getSerializableExtra("list");

        binding.ivCover.setClipToOutline(true);
        if (position != -1) {
            OnScreenMovie item = list.get(position);
            initView(item);
        }
        OnScreenMovieDetailRecyclerAdapter adapter = new OnScreenMovieDetailRecyclerAdapter(list);
        adapter.setOnItemClickListener(p -> {
            binding.rv.smoothScrollToPosition(p);
        });
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rv.addItemDecoration(new RecyclerViewItemDecoration(this, 60, 0, 0));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);
        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE) {
                    View view = snapHelper.findSnapView(binding.rv.getLayoutManager());
                    int position = binding.rv.getChildAdapterPosition(view);
                    initView(list.get(position));
                }
            }
        });
        binding.rv.smoothScrollToPosition(position);
    }

    private void initView(OnScreenMovie item) {
        Glide.with(this)
                .asBitmap()
                .load(item.getImg())
                .into(binding.ivCover);
        binding.tvName.setText(item.getName());
        binding.ratingBar.setRating(Float.parseFloat(item.getRating()) / 2);
        binding.tvRating.setText(item.getRating());
        binding.tvScreenTime.setText("上映时间：" + item.getScreenTime());
        binding.tvStars.setText("主演：" + item.getStar());


        int uid = getSharedPreferences("userInfo", Context.MODE_PRIVATE).getInt("userId", 0);
        model.checkIsMarked(String.valueOf(item.getId()), uid).observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.tvMark.setText(
                        aBoolean ? "已想看" : "想看"
                );
                binding.tvMark.setOnClickListener(v -> {
                    binding.tvMark.setEnabled(false);
                    if (aBoolean) {
                        model.unmarkMovie(String.valueOf(item.getId())).observe(OnScreenMovieDetailActivity.this, new Observer<Integer>() {
                            @Override
                            public void onChanged(Integer aInteger) {
                                Toast.makeText(OnScreenMovieDetailActivity.this, "取消想看", Toast.LENGTH_SHORT).show();
                                binding.tvMark.setText("想看");
                                binding.tvMark.setEnabled(true);
                            }
                        });
                        return;
                    }
                    model.markMovie(new MarkedMovie(String.valueOf(item.getId()), item.getImg(), "0", uid)).observe(OnScreenMovieDetailActivity.this, new Observer<Long>() {
                        @Override
                        public void onChanged(Long aLong) {
                            Toast.makeText(OnScreenMovieDetailActivity.this, "已想看", Toast.LENGTH_SHORT).show();
                            binding.tvMark.setText("已想看");
                            binding.tvMark.setEnabled(true);
                        }
                    });
                });
            }
        });

    }


}
