package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.quanlyquancafeapp.model.TimeWork;
import com.quanlyquancafeapp.model.UserTime;

public class TimeWorkTable {
    public final static String TABLE_NAME = "TimeWork";
    public final static String KEY_ID_TIME_WORK = "id_time_work";
    public final static String KEY_TIME_START = "time_start";
    public final static String KEY_TIME_END = "time_end";

    public static void addTimeWork(TimeWork timeWork) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = DatabaseHelper.context.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID_TIME_WORK,timeWork.getIdTimeWork());
            values.put(KEY_TIME_START,timeWork.getTimeStart());
            values.put(KEY_TIME_END,timeWork.getTimeEnd());

            db.insert(TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
}
