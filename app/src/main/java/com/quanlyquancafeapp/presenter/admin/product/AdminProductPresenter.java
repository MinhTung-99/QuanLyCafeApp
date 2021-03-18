package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminProductPresenter {
    private ProductHelper db;

    public AdminProductPresenter(Context context) {
        db = new ProductHelper(context);
    }

    public ArrayList<Product> getProducts(){
        return db.getProducts();
    }
}
