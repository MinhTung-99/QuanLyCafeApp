package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import java.util.ArrayList;

public class RevenueDetailPresenter {
    DatabaseHelper db;

    public RevenueDetailPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<InvoiceDetail> getInvoiceDetailRevenueDetail(String date){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesRevenueDetail();
        ArrayList<InvoiceDetail> invoiceDetailArrayList = new ArrayList<>();
        for(InvoiceDetail invoiceDetail : invoiceDetails){
            if(invoiceDetail.getIsPay() == 1 && invoiceDetail.getDateBuy().equals(date)){
                invoiceDetailArrayList.add(invoiceDetail);
            }
        }
        return invoiceDetailArrayList;
    }
}
