package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.db.UserTimeTable;
import com.quanlyquancafeapp.db.UserWorkingTable;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.model.UserTime;
import com.quanlyquancafeapp.model.UserWorking;

import java.util.ArrayList;

public class TimekeepingPresenter {
    private DatabaseHelper db;

    public TimekeepingPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public ArrayList<UserTime> getUserTime(String userName){
        ArrayList<User> users = db.getUsers();
        Long idUser = 0L;
        for(User user: users){
            if(user.getUserName().equals(userName)){
                idUser = user.getId();
                break;
            }
        }

        return UserTimeTable.getUserTime(idUser);
    }

    public void addUserTimeWorking(UserWorking userWorking){
        try {
            UserWorkingTable.addUserTimeWorking(userWorking);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserWorking> getUserWorking(){
        return UserWorkingTable.getUserWorking();
    }

    public ArrayList<UserWorking> getUserWorkingByIdUser(String userName){
        ArrayList<User> users = db.getUsers();
        Long idUser = 0L;
        for(User user: users){
            if(user.getUserName().equals(userName)){
                idUser = user.getId();
                break;
            }
        }
        return UserWorkingTable.getUserWorkingByIdUser(idUser);
    }
}
