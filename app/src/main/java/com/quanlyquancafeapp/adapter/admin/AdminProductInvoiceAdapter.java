package com.quanlyquancafeapp.adapter.admin;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminProductInvoiceAdapter extends RecyclerView.Adapter<AdminProductInvoiceAdapter.AdminProductInvoiceHolder>{
    private ArrayList<Product> products;

    public AdminProductInvoiceAdapter(ArrayList<Product> products) {
        this.products = products;
    }
    @NonNull
    @Override
    public AdminProductInvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }
    @Override
    public void onBindViewHolder(@NonNull AdminProductInvoiceHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return products.size();
    }
    class AdminProductInvoiceHolder extends RecyclerView.ViewHolder{
        public AdminProductInvoiceHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
