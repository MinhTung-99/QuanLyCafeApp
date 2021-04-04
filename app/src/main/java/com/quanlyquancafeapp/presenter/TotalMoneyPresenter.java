package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.Constance;

import java.util.ArrayList;

public class TotalMoneyPresenter {
    private DatabaseHelper db;
    private ArrayList<Product> products;
    private ArrayList<InvoiceDetail> invoiceDetails;
    private float intoMoney;
    private float totalMoney;
    private ArrayList<InvoiceDetail> invoicesNotPay;
    private long[] idCurrentInvoiceDetail;
    private int positionId = 0;

    public TotalMoneyPresenter(Context context) {
        db = new DatabaseHelper(context);
        idCurrentInvoiceDetail = new long[30];
    }
    public float handleTotalMoney(Table table, ArrayList<InvoiceDetail> invoicesNotPay, Long idCustomer){
        this.invoicesNotPay = invoicesNotPay;
        totalMoney = 0;
        products = db.getProducts();
        invoiceDetails = db.getDetailInvoices();
        for(int i = 0; i < invoiceDetails.size(); i++){
            if(invoiceDetails.get(i).getIsPay() == 0 && invoiceDetails.get(i).getTypePay().equals(Constance.TYPE_PAY)){
                for (int j = 0; j < products.size(); j++){
                    intoMoney = 0;
                    if(table.getId() != null && invoiceDetails.get(i).getIdTable() != null){
                        if(invoiceDetails.get(i).getIdProduct() == products.get(j).getId() &&
                                invoiceDetails.get(i).getIdTable() == table.getId() &&
                                invoiceDetails.get(i).getCustomer().getId() == idCustomer){
                            handleTotalAndIntoMoneyPay(i,j);
                            setCurrentIdInvoiceDetail(i);
                        }
                    }else{
                        if(invoiceDetails.get(i).getIdProduct() == products.get(j).getId()){
                            handleTotalAndIntoMoneyPay(i,j);
                            setCurrentIdInvoiceDetail(i);
                        }
                    }
                }
            }
        }
        return totalMoney;
    }
    private void setCurrentIdInvoiceDetail(int position){
        idCurrentInvoiceDetail[positionId] = invoiceDetails.get(position).getId();
        positionId++;
    }
    public long[] getCurrentIdInvoiceDetail(){
        return idCurrentInvoiceDetail;
    }
    private void handleTotalAndIntoMoneyPay(int i, int j){
        String saleStr = "";
        for(int s = 0; s < products.get(j).getSale().length(); s++){
            saleStr+=s;
            if(products.get(j).getSale().charAt(s) == '%'){
                break;
            }
        }
        int sale = Integer.parseInt(saleStr);
        float sum = (products.get(j).getPrice() * invoiceDetails.get(i).getCount() * (100-sale)/(float)100);
        intoMoney += sum;
        totalMoney += sum;
        invoiceDetails.get(i).setInToMoney(intoMoney);
        invoicesNotPay.add(invoiceDetails.get(i));
    }
}
