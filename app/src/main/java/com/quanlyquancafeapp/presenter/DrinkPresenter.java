package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class DrinkPresenter {
    private ProductHelper db;

    public DrinkPresenter(Context context) {
        db = new ProductHelper(context);
    }
    public ArrayList<Product> getProducts(){
        return db.getProducts();
    }
}
