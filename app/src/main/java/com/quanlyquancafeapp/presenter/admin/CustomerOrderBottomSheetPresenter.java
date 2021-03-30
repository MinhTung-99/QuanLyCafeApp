package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;

import java.util.ArrayList;

public class CustomerOrderBottomSheetPresenter {

    DatabaseHelper db;

    public CustomerOrderBottomSheetPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<InvoiceDetail> getDetailInvoicesCustomer(Long idCustomer){
        ArrayList<InvoiceDetail> invoiceDetails = new ArrayList<>();
        for(int i = 0; i < db.getDetailInvoicesCustomer().size(); i++){
            if(db.getDetailInvoicesCustomer().get(i).getCustomer().getId() == idCustomer){
                invoiceDetails.add(db.getDetailInvoicesCustomer().get(i));
            }
        }
        return invoiceDetails;
    }
}
