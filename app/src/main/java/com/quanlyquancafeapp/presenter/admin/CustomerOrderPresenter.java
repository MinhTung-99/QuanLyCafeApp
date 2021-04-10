package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class CustomerOrderPresenter {
    DatabaseHelper db;

    public CustomerOrderPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public ArrayList<InvoiceDetail> getDetailInvoicesCustomer(){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesCustomer();

        ArrayList<InvoiceDetail> invoiceDetailArrayList = new ArrayList<>();
        for(int i = 0; i < invoiceDetails.size()-1; i++){
            if(invoiceDetails.get(i).getCustomer().getId() != invoiceDetails.get(i+1).getCustomer().getId()){
                invoiceDetailArrayList.add(invoiceDetails.get(i));
            }
        }
        invoiceDetailArrayList.add(invoiceDetails.get(invoiceDetails.size()-1));
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
