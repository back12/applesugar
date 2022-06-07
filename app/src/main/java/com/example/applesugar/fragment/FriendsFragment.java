package com.example.applesugar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.applesugar.activity.NewPostActivity;
import com.example.applesugar.adapter.FriendsPagerAdapter;
import com.example.applesugar.databinding.FragmentFriendsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class FriendsFragment extends Fragment {
    private FragmentFriendsBinding binding;
    private FriendsPagerAdapter adapter;
    private String[] tabs = new String[]{"推荐", "关注"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFriendsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new FriendsPagerAdapter(getChildFragmentManager(), getLifecycle());
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(0);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        }).attach();

        binding.fab.setOnClickListener(v -> requireContext().startActivity(new Intent(requireContext(), NewPostActivity.class)));
    }
}
