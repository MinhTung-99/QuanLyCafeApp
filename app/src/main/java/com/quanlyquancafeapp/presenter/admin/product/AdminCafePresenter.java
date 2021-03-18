package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminCafePresenter {
    private ProductHelper db;
    public AdminCafePresenter(Context context) {
        db = new ProductHelper(context);
    }
    public ArrayList<Product> getProducts(){
        return db.getProducts();
    }
}
