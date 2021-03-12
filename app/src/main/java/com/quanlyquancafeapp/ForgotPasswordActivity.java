package com.quanlyquancafeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.quanlyquancafeapp.databinding.ActivityForgotPasswordBinding;
import com.quanlyquancafeapp.presenter.ForgotPasswordPresenter;
import com.quanlyquancafeapp.view.IForgotPasswordView;

public class ForgotPasswordActivity extends AppCompatActivity implements IForgotPasswordView {
    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordPresenter forgotPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        forgotPasswordPresenter = new ForgotPasswordPresenter(this);
        binding.btnGetPassword.setBackgroundResource(R.drawable.rounded_white);
        binding.btnGetPassword.setOnClickListener(v->forgotPasswordPresenter.getPassword());
        binding.imgBack.setOnClickListener(v->forgotPasswordPresenter.back());
    }
    @Override
    public void navigateNewPasswordFragment() {
        Intent intent = new Intent(ForgotPasswordActivity.this, NewPasswordActivity.class);
        startActivityForResult(intent, 1001);
    }
    @Override
    public void backToLoginFragment() {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            String result = data.getStringExtra("result");
            if(result.equals("back_twice")){
                finish();
            }
        }
    }
}