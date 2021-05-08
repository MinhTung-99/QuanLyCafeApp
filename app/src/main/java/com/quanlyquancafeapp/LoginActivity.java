package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quanlyquancafeapp.databinding.ActivityLoginBinding;
import com.quanlyquancafeapp.presenter.LoginPresenter;
import com.quanlyquancafeapp.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private ActivityLoginBinding binding;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginPresenter = new LoginPresenter(this, this);

/*        binding.txtForgotPassword.setOnClickListener(v -> navigateToSendOTPActivity("Lấy mật khẩu"));*/

        binding.btnRegisterLogin.setOnClickListener(v -> {
            if(loginPresenter.getSizeUser() > 0){
                String typeUser = loginPresenter.handleLogin(this, LoginActivity.this,
                        binding.edtAccount.getText().toString(), binding.edtPassword.getText().toString());
                if(typeUser.equals("ADMIN")){
                    loginPresenter.navigateToLogInToHomeAdminActivity();
                    finish();
                }else if(typeUser.equals("USER")){
                    loginPresenter.navigateToHomeActivity();
                    finish();
                }
            }else {
                navigateToRegisterActivity();
            }
        });
    }

    @Override
    public void navigateToRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("user_name", binding.edtAccount.getText().toString());
        startActivity(intent);
    }
    @Override
    public void navigateToHomeAdminActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
        startActivity(intent);
    }

    @Override
    public void setTextBtn(String text) {
        binding.btnRegisterLogin.setText(text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(loginPresenter.getSizeUser() > 0){
            setTextBtn("Đăng nhập");
            binding.txtForgotPassword.setVisibility(View.VISIBLE);
        }else {
            setTextBtn("Đăng ký");
            binding.txtForgotPassword.setVisibility(View.INVISIBLE);
        }
    }
}