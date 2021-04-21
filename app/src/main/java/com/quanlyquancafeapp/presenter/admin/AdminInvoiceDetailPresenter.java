package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.utils.Constance;

import java.util.ArrayList;

public class AdminInvoiceDetailPresenter {
    private DatabaseHelper db;
    private ArrayList<Float> totalMoney;

    public AdminInvoiceDetailPresenter(Context context) {
        db = new DatabaseHelper(context);
        totalMoney = new ArrayList<>();
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
        int position = 0;
        for(InvoiceDetail invoiceDetail: invoiceDetails){
            int sale = Integer.parseInt(invoiceDetails.get(position).getSale());
            float sum = invoiceDetail.getProduct().getPrice() * invoiceDetails.get(position).getCount() * (100-sale)/(float)100;
            this.totalMoney.add(sum);
            totalMoney += sum;
            position++;
        }

        return totalMoney;
    }
    public ArrayList<Float> getTotalMoney(){
        return totalMoney;
    }
    public ArrayList<InvoiceDetail> getDetailInvoicesNotTableById(Long id){
        return db.getDetailInvoicesNotTableById(id);
    }

    public User getUserAdmin(){
        ArrayList<User> users = db.getUsers();
        for(User u: users){
            if(u.getTypeUser().equals("ADMIN")){
                return u;
            }
        }
        return null;
    }
}
