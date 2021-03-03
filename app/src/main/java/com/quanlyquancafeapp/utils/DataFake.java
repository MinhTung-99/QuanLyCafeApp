package com.quanlyquancafeapp.utils;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class DataFake {
    public static Order order = new Order();
    public static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<Product> productFake(){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product(1L,"cafe đen đá", R.drawable.ic_cafe_den_da,37000,"CAFE", "10%"));
        products.add(new Product(2L,"cafe sữa",R.drawable.ic_cafe_milk,27000,"CAFE",""));
        products.add(new Product(3L,"7 up", R.drawable.ic_7up, 15000,"DRINK",""));
        products.add(new Product(4L,"coca", R.drawable.ic_coca, 15000,"DRINK",""));
        return products;
    }
}
