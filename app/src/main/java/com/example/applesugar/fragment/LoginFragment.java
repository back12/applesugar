package com.example.applesugar.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.applesugar.activity.MainActivity;
import com.example.applesugar.databinding.FragmentLoginBinding;
import com.example.applesugar.viewmodel.UserViewModel;


public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    private UserViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        Editable textUsername = binding.tilUsername.getEditText().getText();
        Editable textPassword = binding.tilPassword.getEditText().getText();

        binding.tvLogin.setOnClickListener(v -> {
            if (textUsername.length() == 0) {
                binding.tilUsername.setError("用户名不能为空");
                return;
            }
            if (textPassword.length() == 0) {
                binding.tilPassword.setError(("密码不能为空"));
                return;
            }

            model.getUserByName(textUsername.toString()).observe(getViewLifecycleOwner(), user -> {
                if (user == null) {
                    binding.tilUsername.setError("该用户不存在");
                    return;
                }
                if (textPassword.toString().equals(user.getPassword())) {
                    Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                    sp.edit().putInt("userId", user.getUid()).apply();
                    sp.edit().putString("userName", user.getUsername()).apply();
                    sp.edit().putString("avatarUrl", user.getAvatarUrl()).apply();
                    goMain();
                } else {
                    binding.tilPassword.setError("密码错误");
                }
            });


        });

        binding.tilUsername.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    binding.tilUsername.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void goMain() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }


}
