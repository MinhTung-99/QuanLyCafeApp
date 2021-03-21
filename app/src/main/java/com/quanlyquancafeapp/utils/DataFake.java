package com.quanlyquancafeapp.utils;

import com.quanlyquancafeapp.model.InvoiceOld;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class DataFake {
    public static ArrayList<Order> orders = new ArrayList<>();

    public static ArrayList<InvoiceOld> invoiceFake(){
        ArrayList<InvoiceOld> invoices = new ArrayList<>();
        invoices.add(new InvoiceOld(1L,"20/10/2021",1L,1L,1L,5,256,652));
        invoices.add(new InvoiceOld(2L,"06/10/2021",1L,1L,1L,5,256,652));
        return invoices;
    }
    public static ArrayList<Order> orderFake(){
        ArrayList<Order> invoices = new ArrayList<>();
        invoices.add(new InvoiceOld(1L,"20/10/2021",1L,1L,1L,5,256,652));
        invoices.add(new InvoiceOld(2L,"06/10/2021",1L,1L,1L,5,256,652));
        return invoices;
    }
    public static ArrayList<User> userFake(){
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(1L,"ABC","","0987654321","Nam","NGuyen Van A","1234567",""));
        return users;
    }
    public static ArrayList<Table> tableFake(){
        ArrayList<Table> tables = new ArrayList<>();
//        tables.add(new Table(1L,"001","ban 1 tang 1"));
//        tables.add(new Table(2L,"002","ban 2 tang 1"));
        return tables;
    }
}
