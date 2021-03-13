package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

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
        binding.btnRegister.setOnClickListener(v->registerPresenter.backToLoginActivity());
        binding.imgBack.setOnClickListener(v->registerPresenter.backToLoginActivity());
        binding.btnRegister.setOnClickListener(v->{
            registerPresenter.handleRegisterUserAdminDB(this,binding.edtPhoneNumber.getText().toString(),binding.edtNameStore.getText().toString(),
                    binding.edtAddress.getText().toString(), binding.edtUserName.getText().toString(),
                    binding.edtPassword.getText().toString(),binding.edtPasswordAgain.getText().toString());
        });
    }
    @Override
    public void backToLoginActivity() {
        finish();
    }
}