package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;
import android.view.View;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.db.UserTimeTable;
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
        ArrayList<User> users = db.getUsers();
        ArrayList<User> userArrayList = new ArrayList<>();
        for(User user: users){
            if(!user.getTypeUser().equals("ADMIN")){
                userArrayList.add(user);
            }
        }
        return userArrayList;
    }
    public void addUserDB(User user) {
        try {
            db.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateUserDB(User user){
        db.updateUser(user);
    }
    public void deleteUserDB(Long id){
        db.deleteUser(id);
    }
    public void deleteUserTimeByIdUser(Long isUser){
        UserTimeTable.deleteUserTimeByIdUser(isUser);
    }

    public Boolean isSameNameUser(String userName){
        ArrayList<User> users = db.getUsers();
        for (User user: users){
            if(user.getUserName().equals(userName)){
                return true;
            }
        }

        return false;
    }
}
