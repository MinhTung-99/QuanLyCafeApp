package com.quanlyquancafeapp.utils;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class DataFake {
    public static Order order = new Order();
    public static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<Product> productFake(){
        ArrayList<Product> products = new ArrayList<>();
//        products.add(new Product(1L,"cafe đen đá", R.drawable.ic_cafe_den_da,37000,"CAFE", "10%"));
//        products.add(new Product(2L,"cafe sữa",R.drawable.ic_cafe_milk,27000,"CAFE",""));
//        products.add(new Product(3L,"7 up", R.drawable.ic_7up, 15000,"DRINK",""));
//        products.add(new Product(4L,"coca", R.drawable.ic_coca, 15000,"DRINK",""));
        return products;
    }

    public static ArrayList<Invoice> invoiceFake(){
        ArrayList<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1L,"20/10/2021",1L,1L,1L,5,256,652));
        invoices.add(new Invoice(2L,"06/10/2021",1L,1L,1L,5,256,652));
        return invoices;
    }
    public static ArrayList<Order> orderFake(){
        ArrayList<Order> invoices = new ArrayList<>();
        invoices.add(new Invoice(1L,"20/10/2021",1L,1L,1L,5,256,652));
        invoices.add(new Invoice(2L,"06/10/2021",1L,1L,1L,5,256,652));
        return invoices;
    }
    public static ArrayList<User> userFake(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L,"ABC","","0987654321","Nam","NGuyen Van A","1234567",""));
        return users;
    }
    public static ArrayList<Table> tableFake(){
        ArrayList<Table> tables = new ArrayList<>();
        tables.add(new Table(1L,"001","ban 1 tang 1"));
        tables.add(new Table(2L,"002","ban 2 tang 1"));
        return tables;
    }
}
