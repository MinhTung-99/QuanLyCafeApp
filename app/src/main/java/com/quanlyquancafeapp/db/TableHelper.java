package com.quanlyquancafeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class TableHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tableManage";
    private static final String TABLE_TABLES = "tables";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public TableHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLES_TABLE = "CREATE TABLE " + TABLE_TABLES + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT)";
        db.execSQL(CREATE_TABLES_TABLE);
    }
    public void addTable(Table table) throws Exception{
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME,table.getName());
            db.insert(TABLE_TABLES,"",values);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }finally {
            db.close();
        }
    }
    public ArrayList<Table> getTables(){
        ArrayList<Table> tables = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_TABLES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                Table table = new Table();
                table.setId(cursor.getLong(0));
                table.setName(cursor.getString(1));
                tables.add(table);
            } while(cursor.moveToNext());
        }
        return tables;
    }
    public int updateTable(Table table){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,table.getName());
        return db.update(TABLE_TABLES,
                values,
                KEY_ID+"=?",
                new String[]{String.valueOf(table.getId())});
    }
    public int deleteTable(Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_TABLES,
                KEY_ID+"=?",
                new String[]{String.valueOf(id)});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
