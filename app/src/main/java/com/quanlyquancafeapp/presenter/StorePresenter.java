package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class StorePresenter {
    private ProductHelper db;

    public StorePresenter(Context context) {
        db = new ProductHelper(context);
    }

    public ArrayList<Product> getProducts(){
        return db.getProducts();
    }
}
