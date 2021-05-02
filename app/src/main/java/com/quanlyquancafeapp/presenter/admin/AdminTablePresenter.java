package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.view.admin.IAdminTableView;

import java.util.ArrayList;

public class AdminTablePresenter {
    private IAdminTableView tableView;
    private DatabaseHelper db;
    public AdminTablePresenter(IAdminTableView tableView, Context context) {
        this.tableView = tableView;
        db = new DatabaseHelper(context);
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
    public void updateTable(Table table){
        db.updateTable(table);
    }

    public Boolean isSameNameTable(String nameTable){
        ArrayList<Table> tables = db.getTables();
        for (Table table: tables){
            if(table.getName().toLowerCase().equals(nameTable.toLowerCase())){
                return true;
            }
        }

        return false;
    }
}
