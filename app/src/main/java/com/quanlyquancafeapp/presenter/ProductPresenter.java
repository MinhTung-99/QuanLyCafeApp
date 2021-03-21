package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.util.Log;

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
    private Context context;

    public ProductPresenter(IProductView productView, Context context) {
        this.productView = productView;
        this.context = context;
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
        InvoiceDetail invoiceDetail = null;
        int size = 0;
        Invoice invoice = null;
        if(table != null) invoice = new Invoice(1L,db.getUsers().get(1).getId(),productsCafe.get(0).getId(),table.getId(), productsCafe.get(0).getCount(),
                100,100,"20/10/2020",typePay,0);
        else  invoice = new Invoice(1L,db.getUsers().get(1).getId(),productsCafe.get(0).getId(),100L, productsCafe.get(0).getCount(),
                100,100,"20/10/2020",typePay,0);
        try {
            db.addInvoice(invoice);
            size = db.getInvoices().size();
            invoiceDetail = new InvoiceDetail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < productsCafe.size(); i++){
            if(productsCafe.get(i).getCount() > 0){
                try {
                    invoiceDetail.setIdInvoice(db.getInvoices().get(size-1).getId());
                    invoiceDetail.setIdProduct(productsCafe.get(i).getId());
                    invoiceDetail.setCount(productsCafe.get(i).getCount());
                    invoice.setIsPay(0);
                    db.addDetailInvoice(invoiceDetail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        for(int i = 0; i < productsDrink.size(); i++){
//            if(productsDrink.get(i).getCount() > 0){
//                Invoice invoice = new Invoice(1L,1L,productsDrink.get(i).getId(),1L, productsDrink.get(i).getCount(),100,100,"20/10/2020",typePay,0);
//                try {
//                    db.addInvoice(invoice);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
