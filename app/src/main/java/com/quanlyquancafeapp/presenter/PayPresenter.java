package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;

public class PayPresenter {
    private DatabaseHelper db;
    public PayPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public void updateInvoice(Invoice invoice){
        db.updateInvoice(invoice);
    }
}
