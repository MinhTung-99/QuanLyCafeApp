package com.quanlyquancafeapp.presenter;

import com.quanlyquancafeapp.view.IForgotPasswordView;

public class ForgotPasswordPresenter {
    private IForgotPasswordView forgotPasswordView;
    public ForgotPasswordPresenter(IForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
    }
    public void getPassword(){
        forgotPasswordView.navigateNewPasswordFragment();
    }
    public void back(){
        forgotPasswordView.backToLoginFragment();
    }
}
