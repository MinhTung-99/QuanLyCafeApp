package com.quanlyquancafeapp.view;

public interface ILoginView {
    void navigateToSendOTPActivity(String data);
    void navigateToHomeActivity();
    void navigateToHomeAdminActivity();
    void loginFail();
    void setTextBtn(String text);
}
