package com.quanlyquancafeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemProductAdminBinding;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class ProductAdminAdapter extends RecyclerView.Adapter<ProductAdminAdapter.ProductAdminViewHolder>{
    private ArrayList<Product> products;
    private ItemProductAdminBinding binding;
    public ProductAdminAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product_admin, parent, false);
        return new ProductAdminViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdminViewHolder holder, int position) {
        holder.binding.setProduct(products.get(position));
        holder.binding.txtCount.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductAdminViewHolder extends RecyclerView.ViewHolder{
        private ItemProductAdminBinding binding;
        public ProductAdminViewHolder(@NonNull ItemProductAdminBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
