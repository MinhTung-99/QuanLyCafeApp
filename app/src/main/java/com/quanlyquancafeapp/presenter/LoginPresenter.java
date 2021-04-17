package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.view.ILoginView;

import java.util.ArrayList;

public class LoginPresenter {
    private ILoginView loginView;
    private DatabaseHelper db;

    public LoginPresenter(ILoginView signInView, Context context) {
        this.loginView = signInView;
        db = new DatabaseHelper(context);
    }

    public void navigateToHomeActivity(){
        loginView.navigateToHomeActivity();
    }
    public void navigateToLogInToHomeAdminActivity(){
        loginView.navigateToHomeAdminActivity();
    }

    public String handleLogin(Context context,String userName, String password){
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<User> users = db.getUsers();
        for(User user: users){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                return user.getTypeUser();
            }
        }
        loginView.loginFail();
        return "NULL";
    }
    public int getSizeUser(){
        return db.getUsers().size();
    }
}
