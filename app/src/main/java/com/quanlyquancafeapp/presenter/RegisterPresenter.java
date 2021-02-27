package com.quanlyquancafeapp.presenter;

import com.quanlyquancafeapp.view.IRegisterView;

public class RegisterPresenter {
    private IRegisterView registerPresenter;

    public RegisterPresenter(IRegisterView registerPresenter) {
        this.registerPresenter = registerPresenter;
    }
    public void register(){
        registerPresenter.navigateToLoginFragment();
    }
}
