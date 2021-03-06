package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentLoginBinding;
import com.quanlyquancafeapp.presenter.LoginPresenter;
import com.quanlyquancafeapp.view.ILoginView;

public class LoginFragment extends Fragment implements ILoginView {
    private FragmentLoginBinding binding;
    private LoginPresenter loginPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginPresenter = new LoginPresenter(this);
        setBackgroundButton();
        binding.btnRegister.setOnClickListener(v -> loginPresenter.register());
        binding.txtForgotPassword.setOnClickListener(v -> loginPresenter.forgotPassword());
        binding.btnLogin.setOnClickListener(v -> {
            //loginPresenter.logInToHome();
            loginPresenter.logInToAdmin();
        });
    }
    private void setBackgroundButton() {
        binding.btnLogin.setBackgroundResource(R.drawable.rounded_white);
        binding.btnRegister.setBackgroundResource(R.drawable.rounded_white);
    }
    @Override
    public void navigateToRegisterFragment() {
        Log.d("KMFG", "CLICKED");
        Navigation.findNavController(getView()).navigate(R.id.registerFragment);
    }
    @Override
    public void navigateToForgotPasswordFragment() {
        Log.d("KMFG", "CLICKED");
        Navigation.findNavController(getView()).navigate(R.id.forgotPasswordFragment);
    }
    @Override
    public void navigateToHomeFragment() {
        Navigation.findNavController(getView()).navigate(R.id.homeFragment);
    }
    @Override
    public void navigateToAdminFragment() {
        Navigation.findNavController(getView()).navigate(R.id.adminFragment);
    }
}
