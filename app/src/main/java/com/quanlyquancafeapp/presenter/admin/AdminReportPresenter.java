package com.quanlyquancafeapp.presenter.admin;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.PieChartView;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminReportPresenter {
    DatabaseHelper db;

    public AdminReportPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public float getDetailInvoicesRevenueDetailTotalMoney(String date){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesRevenueDetail();
        float totalRevenue = 0;
        for(InvoiceDetail invoiceDetail: invoiceDetails){
            if(invoiceDetail.getDateBuy().equals(date) && invoiceDetail.getIsPay() == 1){
                totalRevenue += invoiceDetail.getCount()*invoiceDetail.getProduct().getPrice();
            }
        }
        return totalRevenue;
    }
    public ArrayList<PieChartView> getDetailInvoicesRevenueDetailPie(String date){
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesRevenueDetail();
        ArrayList<Product> products = db.getProducts();
        ArrayList<PieChartView> pies = new ArrayList<>();

        Integer count = 0;
        boolean isDate = false;
        for (Product product: products){
            PieChartView pie = new PieChartView();
            pie.setDrinks(product.getName());

            for (InvoiceDetail invoiceDetail: invoiceDetails){
                if(invoiceDetail.getDateBuy().equals(date) && invoiceDetail.getIsPay() == 1){
                    isDate = true;
                    if(invoiceDetail.getProduct().getName().equals(product.getName())){
                        count += invoiceDetail.getCount();
                    }
                }
            }
            pie.setCount(count);
            if(isDate){
                pies.add(pie);
                isDate = false;
            }
            count = 0;
        }
        return pies;
    }
}
