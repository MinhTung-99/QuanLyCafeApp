package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class CustomerOrderPresenter {
    DatabaseHelper db;

    public CustomerOrderPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public ArrayList<InvoiceDetail> getDetailInvoicesCustomer(){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesCustomer();
        ArrayList<InvoiceDetail> invoiceDetailArrayList = new ArrayList<>();

        List<String> testName1 = new ArrayList<>();
        List<String> testName2 = new ArrayList<>();

        if(invoiceDetails.size() > 0){
            for(int i = 0; i < invoiceDetails.size(); i++){
                testName1.add(invoiceDetails.get(i).getCustomer().getName());
            }
        }

        for (int i = 0; i < testName1.size(); i++){
            if (!testName2.contains(testName1.get(i))) {
                invoiceDetailArrayList.add(0,invoiceDetails.get(i));
                testName2.add(testName1.get(i));
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
