package com.quanlyquancafeapp.view;

public interface ILoginView {
    void navigateToRegisterActivity();
    void navigateToForgotPasswordActivity();
    void navigateToHomeActivity();
    void navigateToHomeAdminActivity();
    void loginFail();
    void hideView();
    void showView();
}
