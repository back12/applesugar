package com.example.applesugar.fragment;

import android.content.Intent;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.applesugar.activity.MainActivity;
import com.example.applesugar.databinding.FragmentSignupBinding;
import com.example.applesugar.db.entity.User;
import com.example.applesugar.viewmodel.UserViewModel;


public class SignupFragment extends Fragment {
    private FragmentSignupBinding binding;

    private UserViewModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSignupBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model = new ViewModelProvider(requireActivity()).get(UserViewModel.class);

        Editable textUsername = binding.tilUsername.getEditText().getText();
        Editable textPassword = binding.tilPassword.getEditText().getText();
        Editable textPasswordRe = binding.tilPasswordRe.getEditText().getText();

        binding.tvSignup.setOnClickListener(v -> {
            if (textUsername.length() == 0) {
                binding.tilUsername.setError("用户名不能为空");
                return;
            }
            if (textPassword.length() == 0) {
                binding.tilPassword.setError(("密码不能为空"));
                return;
            }
            if(textPasswordRe.length() == 0){
                binding.tilPasswordRe.setError(("密码不能为空"));
                return;
            }
            if(!textPassword.toString().equals(textPasswordRe.toString())){
                binding.tilPasswordRe.setError(("两次密码不一致"));
                return;
            }

            User user = new User(textUsername.toString(), textPassword.toString());

            model.insert(user).observe(getViewLifecycleOwner(), rowId -> {
                if(rowId == -1){
                    binding.tilUsername.setError("用户名已存在");
                    return;
                }
                model.updateAvatar(rowId.intValue(), textUsername.toString());
                Toast.makeText(requireContext(), "注册成功", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
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

}
