package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.utils.Constance;

import java.util.ArrayList;

public class AdminHomePresenter {
    private DatabaseHelper db;

    public AdminHomePresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public String getNameAdmin(){
        ArrayList<User> users = db.getUsers();
        for(User user: users){
            if(user.getTypeUser().equals(Constance.TYPE_USER_ADMIN)){
                return "Welcome " + user.getUserName();
            }
        }

        return "NULL";
    }
}
