package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminProductPresenter {
    private DatabaseHelper db;

    public AdminProductPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public ArrayList<Product> getProducts(){
        return db.getProducts();
    }
}
