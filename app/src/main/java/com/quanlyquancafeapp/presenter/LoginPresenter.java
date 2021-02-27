package com.quanlyquancafeapp.presenter;

import com.quanlyquancafeapp.view.ILoginView;

public class LoginPresenter {
    private ILoginView loginView;
    public LoginPresenter(ILoginView signInView) {
        this.loginView = signInView;
    }
    public void logIn(){
        loginView.navigateToHomeFragment();
    }
    public void register(){
        loginView.navigateToRegisterFragment();
    }
    public void forgotPassword(){
        loginView.navigateToForgotPasswordFragment();
    }
}
