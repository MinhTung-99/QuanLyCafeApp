package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminDrinkPresenter {
    private DatabaseHelper db;

    public AdminDrinkPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public ArrayList<Product> getDrinkProducts(){
        ArrayList<Product> products = db.getProducts();
        ArrayList<Product> drinkProducts = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("DRINK")){
                Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImageByteArr(), 0, product.getImageByteArr().length);
                product.setImageBitmap(bitmap);
                drinkProducts.add(product);
            }
        }
        return  drinkProducts;
    }
    public void deleteProduct(Long id){
        db.deleteProduct(id);
    }
}
