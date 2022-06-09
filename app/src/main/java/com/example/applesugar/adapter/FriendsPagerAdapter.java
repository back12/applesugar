package com.example.applesugar.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.applesugar.fragment.CommentFeedFragment;
import com.example.applesugar.fragment.PostFeedFragment;

public class FriendsPagerAdapter extends FragmentStateAdapter {

    public FriendsPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new PostFeedFragment();
        } else {
            return new CommentFeedFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
