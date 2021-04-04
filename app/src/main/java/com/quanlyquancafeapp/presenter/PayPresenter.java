package com.quanlyquancafeapp.presenter;

import android.content.Context;
import android.util.Log;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class PayPresenter {
    private DatabaseHelper db;
    public PayPresenter(Context context) {
        db = new DatabaseHelper(context);
    }
    public void updateInvoice(Invoice invoice){
        db.updateInvoice(invoice);
    }
    public void updateCountCurrentPeopleTable(Long idCustomer, Long idTable){
        ArrayList<Customer> customers = db.getCustomers();
        ArrayList<Table> tables = db.getTables();

        for(Customer customer: customers){
            if(customer.getId() == idCustomer){
                for(Table table: tables){
                    if(table.getId() == idTable){
                        Log.d("KMFG56", table.getCountCurrentPeople()+" ==="+customer.getCount());
                        table.setCountCurrentPeople(table.getCountCurrentPeople() - customer.getCount());
                        db.updateTable(table);
                    }
                }
            }
        }
    }
}
