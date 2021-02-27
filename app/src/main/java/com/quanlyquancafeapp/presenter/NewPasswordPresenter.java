package com.quanlyquancafeapp.presenter;

import com.quanlyquancafeapp.view.INewPasswordView;

public class NewPasswordPresenter {
    INewPasswordView newPasswordView;
    public NewPasswordPresenter(INewPasswordView newPasswordView) {
        this.newPasswordView = newPasswordView;
    }
    public void backToForgotPassword(){
        newPasswordView.backToForgotPasswordFragment();
    }
    public void backToLogin(){
        newPasswordView.backToLoginFragment();
    }
}
