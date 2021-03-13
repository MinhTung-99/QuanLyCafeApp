package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.view.IAddOrUpdateView;

public class AddOrUpdatePresenter {
    private IAddOrUpdateView addOrUpdateView;
    private ProductHelper db;
    public AddOrUpdatePresenter(IAddOrUpdateView addOrUpdateView, Context context) {
        this.addOrUpdateView = addOrUpdateView;
        db = new ProductHelper(context);
    }
    public void addProductDB(String name, byte[] image, String unit, float price,
                             int availableQuantity, String species, int barcode){
        Product product = new Product(name, image, unit, price, "10%",availableQuantity, species, barcode);
        Log.d("KMFG","OKCLICKED"+product.getName());
        try {
            db.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
