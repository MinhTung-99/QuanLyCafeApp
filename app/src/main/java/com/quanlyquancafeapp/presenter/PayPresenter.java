package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;

import java.util.ArrayList;

public class PayPresenter {
    private DatabaseHelper db;
    public PayPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public void updateInvoice(Invoice invoice){
        db.updateInvoice(invoice);
    }
    public ArrayList<InvoiceDetail> getDetailInvoice(){
        return db.getDetailInvoices();
    }
}
