package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class TablePresenter {
    private DatabaseHelper db;
    public TablePresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<Table> getTables(){
        return db.getTables();
    }
}
