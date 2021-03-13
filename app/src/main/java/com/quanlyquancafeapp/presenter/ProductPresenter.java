package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.view.IProductView;

public class ProductPresenter {
    private IProductView productView;
    private ProductHelper db;
    public ProductPresenter(IProductView productView, Context context) {
        this.productView = productView;
        db = new ProductHelper(context);
    }
}
