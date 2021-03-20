package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.view.ILoginView;

import java.util.ArrayList;

public class LoginPresenter {
    private ILoginView loginView;
    public LoginPresenter(ILoginView signInView) {
        this.loginView = signInView;
    }
    public void navigateToHomeActivity(){
        loginView.navigateToHomeActivity();
    }
    public void navigateToRegisterActivity(){
        loginView.navigateToRegisterActivity();
    }
    public void navigateToForgotPasswordActivity(){
        loginView.navigateToForgotPasswordActivity();
    }
    public void navigateToLogInToHomeAdminActivity(){loginView.navigateToHomeAdminActivity();}
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
    public void visibilityView(Context context){
        DatabaseHelper db = new DatabaseHelper(context);
        ArrayList<User> users = db.getUsers();
        if(users.size() > 0){
            for(User user: users){
                if(user.getTypeUser().equals("ADMIN")){
                    loginView.hideView();
                }
            }
        }else {
            loginView.showView();
        }
    }
}
