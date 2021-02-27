package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.presenter.LoginPresenter;
import com.quanlyquancafeapp.view.ILoginView;

public class LoginFragment extends Fragment implements ILoginView {
    private LoginPresenter loginPresenter;
    private Button btnRegister;
    private Button btnLogin;
    private TextView txtForgotPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        btnRegister.setOnClickListener(v -> loginPresenter.register());
        txtForgotPassword.setOnClickListener(v -> loginPresenter.forgotPassword());
        btnLogin.setOnClickListener(v -> loginPresenter.logIn());
    }
    private void initView() {
        loginPresenter = new LoginPresenter(this);
        btnRegister = getView().findViewById(R.id.btn_register);
        txtForgotPassword = getView().findViewById(R.id.txt_forgot_password);
        btnLogin = getView().findViewById(R.id.btn_login);
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
}
