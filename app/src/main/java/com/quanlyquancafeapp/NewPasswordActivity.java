package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.quanlyquancafeapp.databinding.ActivityNewPasswordBinding;
import com.quanlyquancafeapp.presenter.NewPasswordPresenter;
import com.quanlyquancafeapp.view.INewPasswordView;

public class NewPasswordActivity extends AppCompatActivity implements INewPasswordView {
    private ActivityNewPasswordBinding binding;
    private NewPasswordPresenter newPasswordPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_password);
        newPasswordPresenter = new NewPasswordPresenter(this);
        binding.btnChangePassword.setBackgroundResource(R.drawable.rounded_white);
        binding.imgBack.setOnClickListener(v->newPasswordPresenter.backToForgotPassword());
        binding.btnChangePassword.setOnClickListener(v->newPasswordPresenter.backToLogin());
    }
    @Override
    public void backToForgotPasswordFragment() {
        finish();
    }
    @Override
    public void backToLoginFragment() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","back_twice");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}