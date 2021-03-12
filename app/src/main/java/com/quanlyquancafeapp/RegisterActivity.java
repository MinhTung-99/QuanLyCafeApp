package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.quanlyquancafeapp.databinding.ActivityRegisterBinding;
import com.quanlyquancafeapp.presenter.RegisterPresenter;
import com.quanlyquancafeapp.view.IRegisterView;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    private ActivityRegisterBinding binding;
    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerPresenter = new RegisterPresenter(this);
        binding.btnRegister.setBackgroundResource(R.drawable.rounded_white);
        binding.btnRegister.setOnClickListener(v->registerPresenter.register());
        binding.imgBack.setOnClickListener(v->registerPresenter.register());
    }
    @Override
    public void navigateToLoginFragment() {
        finish();
    }
}