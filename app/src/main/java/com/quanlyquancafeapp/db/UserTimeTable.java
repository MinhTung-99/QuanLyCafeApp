package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.quanlyquancafeapp.model.UserTime;

import java.util.ArrayList;

public class UserTimeTable {
    public static final String TABLE_NAME = "UserTime";
    public final static String KEY_ID_USER_TIME = "id_user_time";
    public final static String KEY_ID_USER = "id_user";
    public final static String KEY_TIME_START = "time_start";
    public final static String KEY_TIME_END = "time_end";

    public static void addUserTime(UserTime userTime) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = DatabaseHelper.context.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_ID_USER,userTime.getId());
            values.put(KEY_TIME_START,userTime.getTimeStart());
            values.put(KEY_TIME_END,userTime.getTimeEnd());

            db.insert(TABLE_NAME,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }

    public static ArrayList<UserTime> getUserTime(Long idUser){
        ArrayList<UserTime> userTimes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id_user=?" ;
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[]{String.valueOf(idUser)});
        //Log.d("KMFG77",cursor.getColumnIndexOrThrow("id_user_time")+" ===");
        if(cursor.moveToFirst()){
            do{
                UserTime userTime = new UserTime();
                userTime.setIdUserTime(cursor.getLong(0));
                userTime.setId(cursor.getLong(1));
                userTime.setTimeStart(cursor.getString(2));
                userTime.setTimeEnd(cursor.getString(3));
                userTimes.add(userTime);
            } while(cursor.moveToNext());
        }
        return userTimes;
    }

    public static int updateUserTime(UserTime userTime){
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TIME_START,userTime.getTimeStart());
        values.put(KEY_TIME_END,userTime.getTimeEnd());

        return db.update(TABLE_NAME,
                values,
                KEY_ID_USER_TIME+"=?",
                new String[]{String.valueOf(userTime.getIdUserTime())});
    }

    public static int deleteUserTime(Long idUserTime){
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();
        return db.delete(TABLE_NAME,
                KEY_ID_USER_TIME+"=?",
                new String[]{String.valueOf(idUserTime)});
    }

    public static int deleteUserTimeByIdUser(Long idUser){
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();
        return db.delete(TABLE_NAME,
                KEY_ID_USER+"=?",
                new String[]{String.valueOf(idUser)});
    }
}
