package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Button;

import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.view.IProductView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProductPresenter {
    private DatabaseHelper db;
    private int sizeInvoice;
    private Invoice invoice;
    private IProductView productView;

    public ProductPresenter(Context context, IProductView productView) {
        db = new DatabaseHelper(context);
        this.productView = productView;
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

    private int countCafe = 0;
    private int countDrink = 0;
    public void handleCount(String typeClick, int position, ArrayList<Product> productsCafe,
                            ArrayList<Product> productsDrink, boolean isCafe, ProductAdapter adapter){
        if(isCafe){
            countCafe = productsCafe.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                countCafe++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                countCafe--;
            }

            checkAvailableQuantity(productsCafe, position);

            productsCafe.get(position).setCount(countCafe);
            adapter.updateProduct(productsCafe);
        }else{
            countDrink = productsDrink.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                countDrink++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                countDrink--;
            }

            checkAvailableQuantity(productsDrink, position);

            productsDrink.get(position).setCount(countDrink);
            adapter.updateProduct(productsDrink);
        }
    }
    private void checkAvailableQuantity(ArrayList<Product> products, int position){
        if(countCafe <= products.get(position).getAvailableQuantity()){
            if(countCafe > 0 || countDrink > 0){
                productView.isEnableBtn(true);
            }else {
                if(countDrink == 0)
                    productView.isEnableBtn(false);
            }
        }else {
            if(countDrink == 0)
                productView.isEnableBtn(false);
        }
    }

    public void addInvoice(ArrayList<Product> productsCafe, ArrayList<Product> productsDrink, String typePay, Table table){
        sizeInvoice = 0;
        setInvoice(table, typePay);
        for(int i = 0; i < productsCafe.size(); i++){
            if(productsCafe.get(i).getCount() > 0){
                try {
                    InvoiceDetail invoiceDetail = setInvoiceDetail(productsCafe, i);
                    if(table == null){
                        db.idTable = 0L;
                    } else {
                        db.idTable = table.getId();
                    }
                    db.addDetailInvoice(invoiceDetail);

                    Product product = productsCafe.get(i);
                    int count = getProductsCafe().get(i).getAvailableQuantity() - product.getCount();
                    product.setAvailableQuantity(count);
                    db.updateProduct(product);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < productsDrink.size(); i++){
            if(productsDrink.get(i).getCount() > 0){
                try {
                    InvoiceDetail invoiceDetail = setInvoiceDetail(productsDrink, i);
                    if(table == null){
                        db.idTable = 0L;
                    } else {
                        db.idTable = table.getId();
                    }
                    db.addDetailInvoice(invoiceDetail);

                    Product product = productsDrink.get(i);
                    int count = getProductsDrink().get(i).getAvailableQuantity() - product.getCount();
                    product.setAvailableQuantity(count);
                    db.updateProduct(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void setInvoice(Table table, String typePay){
        if(table != null){
            SimpleDateFormat getTime = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            invoice = new Invoice(db.getUsers().get(0).getId(),table.getId(), 0,0,"",getTime.format(date),typePay,0);
        } else{
            invoice = new Invoice(db.getUsers().get(0).getId(),0L, 0,0,"","",typePay,0);
        }
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
        invoiceDetail.setSale(products.get(position).getSale());
        invoiceDetail.setDescription(products.get(position).getDescription());
        invoiceDetail.setNameProduct(products.get(position).getName());
        return invoiceDetail;
    }

    public void addCustomer(Customer customer){
        try {
            db.addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTable(Table table){
        db.updateTable(table);
    }

    public void addInvoiceByID
            (ArrayList<Product> productsCafe, ArrayList<Product> productsDrink, String typePay, Table table, Long idCustomer, Long idInvoice, Long idTable){

        for(int i = 0; i < productsCafe.size(); i++){
            if(productsCafe.get(i).getCount() > 0){
                try {
                    InvoiceDetail invoiceDetail = setInvoiceDetailByID(productsCafe, i, idInvoice, idCustomer, idTable);
                    db.addDetailInvoiceById(invoiceDetail);

                    Product product = productsCafe.get(i);
                    int count = getProductsCafe().get(i).getAvailableQuantity() - product.getCount();
                    product.setAvailableQuantity(count);
                    db.updateProduct(product);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for(int i = 0; i < productsDrink.size(); i++){
            if(productsDrink.get(i).getCount() > 0){
                try {
                    InvoiceDetail invoiceDetail = setInvoiceDetailByID(productsDrink, i, idInvoice, idCustomer, idTable);
                    db.addDetailInvoiceById(invoiceDetail);

                    Product product = productsDrink.get(i);
                    int count = getProductsDrink().get(i).getAvailableQuantity() - product.getCount();
                    product.setAvailableQuantity(count);
                    db.updateProduct(product);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private InvoiceDetail setInvoiceDetailByID(ArrayList<Product> products, int position, Long idInvoice, Long idCustomer, Long idTable){
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setIdInvoice(idInvoice);
        invoiceDetail.setIdProduct(products.get(position).getId());
        invoiceDetail.setCount(products.get(position).getCount());
        invoiceDetail.setSale(products.get(position).getSale());
        invoiceDetail.setDescription(products.get(position).getDescription());
        invoiceDetail.getCustomer().setId(idCustomer);
        invoiceDetail.setIdTable(idTable);
        invoiceDetail.setNameProduct(products.get(position).getName());
        return invoiceDetail;
    }
}
