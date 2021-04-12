package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.TimeWork;
import com.quanlyquancafeapp.model.UserTime;

import java.util.ArrayList;

public class TimeWorkTable {
    public final static String TABLE_NAME = "time_work";
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

    public static ArrayList<TimeWork> getTimeWork(){
        ArrayList<TimeWork> timeWorks = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        //Log.d("KMFG77",cursor.getColumnIndexOrThrow("id_time_work")+" ===");
        if(cursor.moveToFirst()){
            do{
                TimeWork timeWork = new TimeWork();
                timeWork.setIdTimeWork(cursor.getLong(0));
                timeWork.setTimeStart(cursor.getString(1));
                timeWork.setTimeEnd(cursor.getString(2));
                timeWorks.add(timeWork);
            } while(cursor.moveToNext());
        }
        return timeWorks;
    }

    public static int updateTimeWork(TimeWork timeWork){
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID_TIME_WORK,timeWork.getIdTimeWork());
        values.put(KEY_TIME_START,timeWork.getTimeStart());
        values.put(KEY_TIME_END,timeWork.getTimeEnd());

        return db.update(TABLE_NAME,
                values,
                KEY_ID_TIME_WORK+"=?",
                new String[]{String.valueOf(timeWork.getIdTimeWork())});
    }

    public static int deleteTimeWork(Long id){
        SQLiteDatabase db = DatabaseHelper.context.getWritableDatabase();
        return db.delete(TABLE_NAME,
                KEY_ID_TIME_WORK+"=?",
                new String[]{String.valueOf(id)});
    }
}
