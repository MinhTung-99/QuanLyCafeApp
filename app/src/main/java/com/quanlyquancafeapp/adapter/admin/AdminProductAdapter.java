package com.quanlyquancafeapp.adapter.admin;

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

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.ProductAdminViewHolder>{
    private ArrayList<Product> products;
    private ItemProductAdminBinding binding;
    private RecyclerViewItemOnclick recyclerViewItemOnclick;
    public AdminProductAdapter(ArrayList<Product> products, RecyclerViewItemOnclick recyclerViewItemOnclick) {
        this.products = products;
        this.recyclerViewItemOnclick = recyclerViewItemOnclick;
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
        holder.binding.btnDelete.setOnClickListener(v->recyclerViewItemOnclick.btnDelete(products.get(position)));
        holder.binding.btnUpdate.setOnClickListener(v->recyclerViewItemOnclick.btnUpdate(products.get(position)));
        holder.itemView.setOnLongClickListener(v->{
            recyclerViewItemOnclick.onLongClick(products.get(position));
            return false;
        });
    }
    public void updateProduct(ArrayList<Product> products){
        this.products = products;
        notifyDataSetChanged();
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
    public interface RecyclerViewItemOnclick{
        void btnUpdate(Product product);
        void btnDelete(Product product);
        void onLongClick(Product product);
    }
}
