package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.view.IProductView;

import java.util.ArrayList;

public class ProductPresenter {
    private IProductView productView;
    private DatabaseHelper db;
    private int sizeInvoice;
    private Invoice invoice;

    public ProductPresenter(IProductView productView, Context context) {
        this.productView = productView;
        db = new DatabaseHelper(context);
    }
    public ArrayList<Product> getProductsCafe(){
        ArrayList<Product> products = db.getProducts();
        ArrayList<Product> productsCafe = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                productsCafe.add(product);
            }
        }
        return productsCafe;
    }
    public ArrayList<Product> getProductsDrink(){
        ArrayList<Product> products = db.getProducts();
        ArrayList<Product> productsDrink = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("DRINK")){
                productsDrink.add(product);
            }
        }
        return productsDrink;
    }
    public void handleCount(String typeClick, int position, ArrayList<Product> productsCafe,
                            ArrayList<Product> productsDrink, boolean isCafe, ProductAdapter adapter){
        if(isCafe){
            int count = productsCafe.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                count++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                count--;
            }
            productsCafe.get(position).setCount(count);
            adapter.updateProduct(productsCafe);
        }else{
            int count = productsDrink.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                count++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                count--;
            }
            productsDrink.get(position).setCount(count);
            adapter.updateProduct(productsDrink);
        }
    }
    public void addInvoice(ArrayList<Product> productsCafe, ArrayList<Product> productsDrink, String typePay, Table table){
        sizeInvoice = 0;
        setInvoice(table, typePay);
        for(int i = 0; i < productsCafe.size(); i++){
            if(productsCafe.get(i).getCount() > 0){
                try {
                    InvoiceDetail invoiceDetail = setInvoiceDetail(productsCafe, i);
                    db.addDetailInvoice(invoiceDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < productsDrink.size(); i++){
            if(productsDrink.get(i).getCount() > 0){
                try {
                    InvoiceDetail invoiceDetail = setInvoiceDetail(productsDrink, i);
                    db.addDetailInvoice(invoiceDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void setInvoice(Table table, String typePay){
        if(table != null) invoice = new Invoice(db.getUsers().get(1).getId(),table.getId(), 0,0,"","",typePay,0);
        else  invoice = new Invoice(db.getUsers().get(1).getId(),0L, 0,0,"","",typePay,0);
        try {
            db.addInvoice(invoice);
            sizeInvoice = db.getInvoices().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private InvoiceDetail setInvoiceDetail(ArrayList<Product> products, int position){
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setIdInvoice(db.getInvoices().get(sizeInvoice-1).getId());
        invoiceDetail.setIdProduct(products.get(position).getId());
        invoiceDetail.setCount(products.get(position).getCount());
        return invoiceDetail;
    }
}
