package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;

import java.util.ArrayList;

public class AdminInvoiceDetailPresenter {
    DatabaseHelper db;

    public AdminInvoiceDetailPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<InvoiceDetail> getDetailInvoicesById(Long id){
        return db.getDetailInvoicesById(id);
    }
}
