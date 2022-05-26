package com.example.applesugar.activity;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applesugar.adapter.OnScreenMovieRecyclerAdapter;
import com.example.applesugar.databinding.ActivityOnScreenMovieBinding;
import com.example.applesugar.db.entity.OnScreenMovie;
import com.example.applesugar.utils.RecyclerViewItemDecoration;

import java.util.List;

public class OnScreenMovieActivity extends AppCompatActivity {
    private ActivityOnScreenMovieBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnScreenMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int position = getIntent().getIntExtra("position", -1);
        List<OnScreenMovie> list = (List<OnScreenMovie>) getIntent().getSerializableExtra("list");

        binding.ivCover.setClipToOutline(true);
        if(position!= -1){
            OnScreenMovie item = list.get(position);
            initView(item);
        }

        binding.rv.setAdapter(new OnScreenMovieRecyclerAdapter(list));
        binding.rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rv.addItemDecoration(new RecyclerViewItemDecoration(this, 60, 0, 0));
        PagerSnapHelper snapHelper =  new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv);
        binding.rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == SCROLL_STATE_IDLE){
                    View view = snapHelper.findSnapView(binding.rv.getLayoutManager());
                    int position = binding.rv.getChildAdapterPosition(view);
                    initView(list.get(position));
                }
            }
        });

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
    }

}
