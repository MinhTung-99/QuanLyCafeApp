package com.quanlyquancafeapp.presenter.admin.product;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminCafePresenter {
    private ProductHelper db;
    public AdminCafePresenter(Context context) {
        db = new ProductHelper(context);
    }
    public ArrayList<Product> getCafeProducts(){
        ArrayList<Product> products = db.getProducts();
        ArrayList<Product> cafeProducts = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                if(product.getImageByteArr() != null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImageByteArr(), 0, product.getImageByteArr().length);
                    product.setImageBitmap(bitmap);
                }
                cafeProducts.add(product);
            }
        }
        return cafeProducts;
    }
    public void deleteProduct(Long id){
        db.deleteProduct(id);
    }
}
