package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.view.admin.IAdminUserView;

import java.util.ArrayList;

public class AdminUserPresenter {
    private IAdminUserView userView;
    private DatabaseHelper db;

    public AdminUserPresenter(IAdminUserView userView, Context context) {
        this.userView = userView;
        db = new DatabaseHelper(context);
    }
    public ArrayList<User> getUsersDB(){
        return db.getUsers();
    }
    public void addUserDB(String userName, String phoneNumber, String password, String gender) {
        try {
            User user = new User(phoneNumber, gender, userName, password, "USER");
            db.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateUserDB(Long id,String userName, String phoneNumber, String password, String gender){
        User user = new User(phoneNumber, gender, userName, password, "USER");
        user.setId(id);
        db.updateUser(user);
    }
    public void deleteUserDB(Long id){
        db.deleteUser(id);
    }
}
