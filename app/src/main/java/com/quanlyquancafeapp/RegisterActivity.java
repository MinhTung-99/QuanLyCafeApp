package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.quanlyquancafeapp.databinding.ActivityRegisterBinding;
import com.quanlyquancafeapp.presenter.RegisterPresenter;
import com.quanlyquancafeapp.view.IRegisterView;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {
    private ActivityRegisterBinding binding;
    private RegisterPresenter registerPresenter;
    private String newPassword, newPasswordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerPresenter = new RegisterPresenter(this);

        binding.txtPhoneNumber.setText(getIntent().getStringExtra("phone_number"));

        binding.btnRegister.setOnClickListener(v->registerPresenter.backToLoginActivity());

        binding.imgBack.setOnClickListener(v->registerPresenter.backToLoginActivity());

        binding.btnRegister.setOnClickListener(v->{
            registerPresenter.handleRegisterUserAdminDB(this,binding.txtPhoneNumber.getText().toString(),binding.edtNameStore.getText().toString(),
                    binding.edtAddress.getText().toString(), binding.edtUserName.getText().toString(),
                    binding.edtPassword.getText().toString(),binding.edtPasswordAgain.getText().toString());
            finish();
        });

        setupPasswordEdit();
    }

    @Override
    public void backToLoginActivity() {
        finish();
    }

    private void setupPasswordEdit(){
        binding.edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPassword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(registerPresenter.isSamePassword(newPassword, newPasswordAgain)){
                    binding.btnRegister.setEnabled(true);
                }else {
                    binding.btnRegister.setEnabled(false);
                }
            }
        });
        binding.edtPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPasswordAgain = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(registerPresenter.isSamePassword(newPassword, newPasswordAgain)){
                    binding.btnRegister.setEnabled(true);
                }else {
                    binding.btnRegister.setEnabled(false);
                }
            }
        });
    }
}