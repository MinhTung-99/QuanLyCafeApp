package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.TableHelper;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.view.ITableAdminView;

import java.util.ArrayList;

public class TableAdminPresenter {
    private ITableAdminView tableView;
    private TableHelper db;
    public TableAdminPresenter(ITableAdminView tableView, Context context) {
        this.tableView = tableView;
        db = new TableHelper(context);
    }
    public void addTable(Table table){
        try {
            db.addTable(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Table> getTables(){
        return db.getTables();
    }
    public void deleteTable(Long id){
        db.deleteTable(id);
    }
}
