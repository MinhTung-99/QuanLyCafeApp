package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderPresenter {
    private DatabaseHelper db;

    public CustomerOrderPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public ArrayList<InvoiceDetail> getDetailInvoicesCustomer(){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesCustomer();
        ArrayList<InvoiceDetail> invoiceDetailArrayList = new ArrayList<>();

        List<Long> idCustomerFull = new ArrayList<>();
        List<Long> idCustomer = new ArrayList<>();

        if(invoiceDetails.size() > 0){
            for(int i = 0; i < invoiceDetails.size(); i++){
                idCustomerFull.add(invoiceDetails.get(i).getCustomer().getId());
            }
        }

        for (int i = 0; i < idCustomerFull.size(); i++){
            if (!idCustomer.contains(idCustomerFull.get(i))) {
                invoiceDetailArrayList.add(0,invoiceDetails.get(i));
                idCustomer.add(idCustomerFull.get(i));
            }
        }

        return invoiceDetailArrayList;
    }

    public int getSizeUser(){
        return db.getUsers().size();
    }
    public User getUserAdmin(){
        ArrayList<User> users = db.getUsers();
        User user = null;
        for(User u: users){
            if(u.getTypeUser().equals("ADMIN")){
                user = u;
                break;
            }
        }
        return user;
    }
}
