package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;

import java.util.ArrayList;

public class AdminInvoicePresenter {
    private DatabaseHelper db;
    public AdminInvoicePresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<Invoice> getInvoiceSort(){
        ArrayList<Invoice> invoices = db.getInvoicesSort();
        ArrayList<Invoice> invoiceArrayList = new ArrayList<>();
        for(Invoice invoice: invoices){
            if(!invoice.getDateBuy().equals("")){
                invoiceArrayList.add(invoice);
            }
        }
        return invoiceArrayList;
    }
}
