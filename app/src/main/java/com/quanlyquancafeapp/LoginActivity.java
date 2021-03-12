package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.quanlyquancafeapp.databinding.ActivityLoginBinding;
import com.quanlyquancafeapp.presenter.LoginPresenter;
import com.quanlyquancafeapp.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private ActivityLoginBinding binding;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
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
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    public void navigateToForgotPasswordFragment() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
    @Override
    public void navigateToHomeFragment() {
        //Navigation.findNavController(getView()).navigate(R.id.homeFragment);
    }
    @Override
    public void navigateToAdminFragment() {
        Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
        startActivity(intent);
//        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//        startActivity(intent);
    }
}