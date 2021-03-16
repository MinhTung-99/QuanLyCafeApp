package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.widget.Button;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.db.ProductHelper;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.view.IProductView;

import java.util.ArrayList;

public class ProductPresenter {
    private IProductView productView;
    private ProductHelper db;
    private Context context;

    public ProductPresenter(IProductView productView, Context context) {
        this.productView = productView;
        this.context = context;
        db = new ProductHelper(context);
    }
    public ArrayList<Product> getProductsCafe(){
        ArrayList<Product> products = db.getProducts();
        ArrayList<Product> productsCafe = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                productsCafe.add(product);
            }
        }
        return productsCafe;
    }
    public ArrayList<Product> getProductsDrink(){
        ArrayList<Product> products = db.getProducts();
        ArrayList<Product> productsDrink = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("DRINK")){
                productsDrink.add(product);
            }
        }
        return productsDrink;
    }
    public void setBackgroundButton(Button button, int color){
        button.setBackgroundColor(context.getResources().getColor(color));
    }
    public void handleCount(String typeClick, int position, ArrayList<Product> productsCafe,
                            ArrayList<Product> productsDrink, boolean isCafe, ProductAdapter adapter){
        if(isCafe){
            int count = productsCafe.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                count++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                count--;
            }
            productsCafe.get(position).setCount(count);
            adapter.updateProduct(productsCafe);
        }else{
            int count = productsDrink.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                count++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                count--;
            }
            productsDrink.get(position).setCount(count);
            adapter.updateProduct(productsDrink);
        }
    }
}
