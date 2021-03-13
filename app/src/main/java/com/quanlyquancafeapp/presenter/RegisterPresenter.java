package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.widget.Toast;

import com.quanlyquancafeapp.db.UserHelper;
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
        UserHelper db = new UserHelper(context);
        User user = new User(nameStore, address, phoneNumber, userName, password, "ADMIN");
        try {
            if(password.equals(passwordAgain)){
                db.addUser(user);
                Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
