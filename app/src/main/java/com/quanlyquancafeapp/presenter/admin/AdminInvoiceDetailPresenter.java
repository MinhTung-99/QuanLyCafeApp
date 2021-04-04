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
//    public ArrayList<InvoiceDetail> getDetailInvoicesById(Long id){
//        return db.getDetailInvoicesById(id);
//    }
//    public float getDetailInvoicesByIdTotalMoney(Long id){
//        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesById(id);
//        float totalMoney = 0;
//        for(InvoiceDetail invoiceDetail: invoiceDetails){
//            totalMoney += invoiceDetail.getCount()*invoiceDetail.getProduct().getPrice();
//        }
//
//        return totalMoney;
//    }
    public float getDetailInvoicesNotTableByIdTotalMoney(Long id){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesNotTableById(id);
        float totalMoney = 0;
        for(InvoiceDetail invoiceDetail: invoiceDetails){
            totalMoney += invoiceDetail.getCount()*invoiceDetail.getProduct().getPrice();
        }

        return totalMoney;
    }
    public ArrayList<InvoiceDetail> getDetailInvoicesNotTableById(Long id){
        return db.getDetailInvoicesNotTableById(id);
    }
}
