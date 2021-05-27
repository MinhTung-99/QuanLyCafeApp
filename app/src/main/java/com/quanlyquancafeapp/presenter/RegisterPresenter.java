package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.view.IRegisterView;

public class RegisterPresenter {
    private IRegisterView registerPresenter;

    public RegisterPresenter(IRegisterView registerPresenter) {
        this.registerPresenter = registerPresenter;
    }
    public void backToLoginActivity(){
        registerPresenter.backToLoginActivity();
    }
    public void handleRegisterUserAdminDB(Context context, String phoneNumber, String nameStore,String address ,String userName, String password, String passwordAgain){
        DatabaseHelper db = new DatabaseHelper(context);
        User user = new User(nameStore, address, phoneNumber, userName, password, "ADMIN");
        try {
            db.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean isSamePassword(String newPassword, String newPasswordAgain){
        if(newPassword.equals(newPasswordAgain)){
            return true;
        }
        return false;
    }
}
