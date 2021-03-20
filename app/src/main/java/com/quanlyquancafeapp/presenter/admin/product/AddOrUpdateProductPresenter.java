package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.view.admin.IAddOrUpdateView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AddOrUpdateProductPresenter {
    private IAddOrUpdateView addOrUpdateView;
    private DatabaseHelper db;
    public AddOrUpdateProductPresenter(IAddOrUpdateView addOrUpdateView, Context context) {
        this.addOrUpdateView = addOrUpdateView;
        db = new DatabaseHelper(context);
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
    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
