package com.example.applesugar.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.applesugar.databinding.ActivityNewPostBinding;
import com.example.applesugar.db.entity.Post;
import com.example.applesugar.viewmodel.PostViewModel;


public class NewPostActivity extends AppCompatActivity {
    private ActivityNewPostBinding binding;
    private int count = 0;
    private PostViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = new ViewModelProvider(this).get(PostViewModel.class);
        initView();
    }

    private void initView() {
        binding.ivBack.setOnClickListener(v -> finish());
        binding.tvPost.setOnClickListener(v -> {
            String content = binding.etContent.getText().toString();
            if(content.length() < 6){
                Toast.makeText(NewPostActivity.this, "字符数不得小于6个", Toast.LENGTH_SHORT).show();
                return;
            }
            SharedPreferences sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            String username = sp.getString("userName", "");
            int uid = sp.getInt("userId", 0);
            Post post = new Post(username, content, uid);
            model.insertPost(post).observe(this, aLong -> {
                Toast.makeText(NewPostActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
        binding.etContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                count = binding.etContent.getText().length();
                binding.tvWordCount.setText(count + "/100");
            }
        });
        binding.etContent.requestFocus();
        InputMethodManager inputManager =(InputMethodManager)binding.etContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                inputManager.showSoftInput(binding.etContent, 0);
            }
        }, 100);

    }
}
