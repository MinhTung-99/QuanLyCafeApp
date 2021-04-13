package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.db.UserTimeTable;
import com.quanlyquancafeapp.model.UserTime;

import java.util.ArrayList;

public class AdminTimeWorkPresenter {
    DatabaseHelper db;

    public AdminTimeWorkPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public void addTimeWork(UserTime userTime){
        try {
            UserTimeTable.addUserTime(userTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserTime> getUserTime(Long idUser){
        return UserTimeTable.getUserTime(idUser);
    }

    public int updateUserTime(UserTime userTime){
        return UserTimeTable.updateUserTime(userTime);
    }

    public int deleteTimeWork(Long id){
        return UserTimeTable.deleteUserTime(id);
    }
}
