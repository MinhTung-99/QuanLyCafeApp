package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.view.IAddOrUpdateView;

public class AddOrUpdateProductPresenter {
    private IAddOrUpdateView addOrUpdateView;
    private ProductHelper db;
    public AddOrUpdateProductPresenter(IAddOrUpdateView addOrUpdateView, Context context) {
        this.addOrUpdateView = addOrUpdateView;
        db = new ProductHelper(context);
    }
    public void addProductDB(Product product){
        try {
            db.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProductDB(Product product){
        db.updateProduct(product);
    }
}
