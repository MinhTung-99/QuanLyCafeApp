package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class UserHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userManage";
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME_STORE = "name_store";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_TYPE_USER = "type_user";

    public UserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME_STORE + " TEXT," +
                KEY_ADDRESS + " TEXT," +
                KEY_PHONE_NUMBER + " TEXT," +
                KEY_GENDER + " TEXT," +
                KEY_USERNAME + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_TYPE_USER + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }
    public void addUser(User user) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME_STORE,user.getNameStore());
            values.put(KEY_ADDRESS,user.getAddress());
            values.put(KEY_PHONE_NUMBER, user.getPhoneNumber());
            values.put(KEY_GENDER,user.getGender());
            values.put(KEY_USERNAME, user.getUserName());
            values.put(KEY_PASSWORD, user.getPassword());
            values.put(KEY_TYPE_USER, user.getTypeUser());
            db.insert(TABLE_USERS,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<User> getUsers(){
        ArrayList<User> users = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getLong(0));
                user.setNameStore(cursor.getString(1));
                user.setAddress(cursor.getString(2));
                user.setPhoneNumber(cursor.getString(3));
                user.setGender(cursor.getString(4));
                user.setUserName(cursor.getString(5));
                user.setPassword(cursor.getString(6));
                user.setTypeUser(cursor.getString(7));
                users.add(user);
            } while(cursor.moveToNext());
        }
        return users;
    }
    public int updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME,user.getUserName());
        values.put(KEY_PHONE_NUMBER,user.getPhoneNumber());
        values.put(KEY_GENDER,user.getGender());
        values.put(KEY_PASSWORD,user.getPassword());
        return db.update(TABLE_USERS,
                values,
                KEY_ID+"=?",
                new String[]{String.valueOf(user.getId())});
    }
    public int deleteUser(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USERS,
                KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
