package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.quanlyquancafeapp.databinding.ActivityNewPasswordBinding;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.presenter.NewPasswordPresenter;
import com.quanlyquancafeapp.view.INewPasswordView;

public class NewPasswordActivity extends AppCompatActivity implements INewPasswordView {
    private ActivityNewPasswordBinding binding;
    private NewPasswordPresenter newPasswordPresenter;
    private String newPassword, newPasswordAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_new_password);
        newPasswordPresenter = new NewPasswordPresenter( this);
        binding.imgBack.setOnClickListener(v->backToLoginActivity());
        binding.btnChangePassword.setOnClickListener(v->{
            backToLoginActivity();

            User user = newPasswordPresenter.getUserAdmin();
            user.setPassword(binding.edtNewPassword.getText().toString());
            newPasswordPresenter.updatePassword(user);
        });

        setupPasswordEdit();
    }
    private void setupPasswordEdit(){
        binding.edtNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPassword = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(newPasswordPresenter.isSamePassword(newPassword, newPasswordAgain)){
                    binding.btnChangePassword.setEnabled(true);
                }else {
                    binding.btnChangePassword.setEnabled(false);
                }
            }
        });
        binding.edtNewPasswordAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newPasswordAgain = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(newPasswordPresenter.isSamePassword(newPassword, newPasswordAgain)){
                    binding.btnChangePassword.setEnabled(true);
                }else {
                    binding.btnChangePassword.setEnabled(false);
                }
            }
        });
    }
    @Override
    public void backToLoginActivity() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result","back");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}