package com.quanlyquancafeapp.presenter.admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.db.TimeWorkTable;
import com.quanlyquancafeapp.db.UserTable;
import com.quanlyquancafeapp.model.TimeWork;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class AdminTimeWorkPresenter {
    DatabaseHelper db;

    public AdminTimeWorkPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public void addTimeWork(TimeWork timeWork){
        try {
            TimeWorkTable.addTimeWork(timeWork);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<TimeWork> getTimeWork(){
        return TimeWorkTable.getTimeWork();
    }

    public int updateTimeWork(TimeWork timeWork){
        return TimeWorkTable.updateTimeWork(timeWork);
    }

    public int deleteTimeWork(Long id){
        return TimeWorkTable.deleteTimeWork(id);
    }
}
