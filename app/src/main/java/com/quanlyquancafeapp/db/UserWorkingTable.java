package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.quanlyquancafeapp.model.UserTime;
import com.quanlyquancafeapp.model.UserWorking;

import java.util.ArrayList;

public class UserWorkingTable {
    public final static String TABLE_NAME = "UserWorking";
    public final static String KEY_ID_USER = "id_user";
    public final static String KEY_DATE = "date";
    public final static String KEY_TIME_START = "time_start_work";
    public final static String KEY_TIME_END = "time_end_work";

    public static void addUserTimeWorking(UserWorking userWorking) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = DatabaseHelper.context.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID_USER, userWorking.getIdUser());
            values.put(KEY_DATE, userWorking.getDate());
            values.put(KEY_TIME_START, userWorking.getTimeStart());
            values.put(KEY_TIME_END, userWorking.getTimeEnd());

            db.insert(TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }

    public static ArrayList<UserWorking> getUserWorking(){
        ArrayList<UserWorking> userWorkings = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //Log.d("KMFG77",cursor.getColumnIndexOrThrow("id_user_time")+" ===");
        if(cursor.moveToFirst()){
            do{
                UserWorking userWorking = new UserWorking();
                userWorking.setIdUser(cursor.getLong(0));
                userWorking.setDate(cursor.getString(1));
                userWorking.setTimeStart(cursor.getString(2));
                userWorking.setTimeEnd(cursor.getString(3));
                userWorkings.add(userWorking);
            } while(cursor.moveToNext());
        }
        return userWorkings;
    }
}
