package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.DataFake;

import java.util.ArrayList;

public class TotalMoneyPresenter {
    private DatabaseHelper db;
    private ArrayList<Product> products;
    private ArrayList<Invoice> invoices;
    private float intoMoney;
    private float totalMoney;
    private ArrayList<Invoice> invoicesNotPay;

    public TotalMoneyPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public float handleTotalMoney(Table table, ArrayList<Invoice> invoicesNotPay){
        this.invoicesNotPay = invoicesNotPay;
        totalMoney = 0;
        products = db.getProducts();
        invoices = db.getInvoices();
        for(int i = 0; i < invoices.size(); i++){
            if(invoices.get(i).getIsPay() == 0){
                for (int j = 0; j < products.size(); j++){
                    intoMoney = 0;
                    if(table != null){
                        if(invoices.get(i).getIdProduct() == products.get(j).getId() &&
                                invoices.get(i).getIdTable() == table.getId()){
                            if(products.get(j).getSale().equals("")){//check sale
                                float sum = products.get(j).getPrice() * DataFake.orders.get(i).getCount();
                                intoMoney += sum;
                                totalMoney += sum;
                            }else {
                                String saleStr = "";
                                for(int s = 0; s < products.get(j).getSale().length(); s++){
                                    saleStr+=s;
                                    if(products.get(j).getSale().charAt(s) == '%'){
                                        break;
                                    }
                                }
                                int sale = Integer.parseInt(saleStr);
                                float sum = (products.get(j).getPrice() * DataFake.orders.get(i).getCount() * (100-sale)/(float)100);
                                intoMoney += sum;
                                totalMoney += sum;
                            }
                            invoices.get(i).setTotalMoney(intoMoney);
                            db.updateInvoice(invoices.get(i));
                        }
                    }else{
                        handleTotalAndIntoMoneyPayAtTheCounter(i,j);
                    }
                }
            }
        }
        return totalMoney;
    }
    private void handleTotalAndIntoMoneyPayAtTheCounter(int i, int j){
        if(invoices.get(i).getIdProduct() == products.get(j).getId()){
            String saleStr = "";
            for(int s = 0; s < products.get(j).getSale().length(); s++){
                saleStr+=s;
                if(products.get(j).getSale().charAt(s) == '%'){
                    break;
                }
            }
            int sale = Integer.parseInt(saleStr);
            float sum = (products.get(j).getPrice() * invoices.get(i).getCount() * (100-sale)/(float)100);
            intoMoney += sum;
            totalMoney += sum;
            invoices.get(i).setInToMoney(intoMoney);
            invoicesNotPay.add(invoices.get(i));
            db.updateInvoice(invoices.get(i));
        }
    }
}
