package com.quanlyquancafeapp.view;

public interface ILoginView {
    void navigateToSendOTPActivity();
    void navigateToHomeActivity();
    void navigateToHomeAdminActivity();
    void loginFail();
    void setTextBtn(String text);
}
