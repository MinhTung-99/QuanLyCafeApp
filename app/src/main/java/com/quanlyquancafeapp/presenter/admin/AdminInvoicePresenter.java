package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;

import java.util.ArrayList;

public class AdminInvoicePresenter {
    private DatabaseHelper db;
    public AdminInvoicePresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<Invoice> getInvoiceSort(){
        return db.getInvoicesSort();
    }
}
