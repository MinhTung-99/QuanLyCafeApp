package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.quanlyquancafeapp.model.UserTime;

public class UserTimeTable {
    public static final String TABLE_NAME = "UserTime";
    public final static String KEY_ID_USER = "id_user";
    public final static String KEY_TIME_WORK = "time_work";

    public static void addUserTime(UserTime userTime) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = DatabaseHelper.context.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID_USER,userTime.getId());
            values.put(KEY_TIME_WORK,userTime.getTimeWork());

            db.insert(TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
}
