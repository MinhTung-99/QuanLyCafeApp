package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class CafePresenter {
    private ProductHelper db;
    public CafePresenter(Context context) {
        db = new ProductHelper(context);
    }
    public ArrayList<Product> getProducts(){
        return db.getProducts();
    }
}
