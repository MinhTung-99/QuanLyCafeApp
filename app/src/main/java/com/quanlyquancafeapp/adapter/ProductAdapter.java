package com.quanlyquancafeapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemProductBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;
import com.quanlyquancafeapp.utils.PriceUtil;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private ArrayList<Product> products;
    private IRecyclerViewOnItemClick recyclerViewOnItemClick;

    public ProductAdapter(ArrayList<Product> products, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.products = products;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }
    public void updateProduct(ArrayList<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_product, parent, false);
        return new ProductViewHolder(binding, recyclerViewOnItemClick);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        convertImageByteArrToBitmap(position);
        holder.binding.imgProduct.setImageBitmap(products.get(position).getImageBitmap());
        holder.binding.txtName.setText(products.get(position).getName() + " - ");
        String setupPrice = PriceUtil.setupPrice(String.valueOf(products.get(position).getPrice()));
        String setupPriceByComma = PriceUtil.getPriceByComma(setupPrice);
        holder.binding.txtPrice.setText(setupPriceByComma+"k");
        holder.binding.txtCount.setText(String.valueOf(products.get(position).getCount()));

        holder.itemView.setOnClickListener(v->holder.recyclerViewOnItemClick.onClick(position));
        holder.binding.btnReduction.setOnClickListener(v->holder.recyclerViewOnItemClick.reductionBtn(position));

        if(products.get(position).getCount() == 0){
            hideOrShowView(holder, View.GONE);
        }else {
            hideOrShowView(holder, View.VISIBLE);
        }
    }
    private void convertImageByteArrToBitmap(int position){
        Bitmap bitmap = BitmapFactory.decodeByteArray(products.get(position).getImageByteArr(), 0, products.get(position).getImageByteArr().length);
        products.get(position).setImageBitmap(bitmap);
    }
    private void hideOrShowView(ProductViewHolder holder, int visibility){
        holder.binding.btnReduction.setVisibility(visibility);
        holder.binding.txtCount.setVisibility(visibility);
    }
    @Override
    public int getItemCount() {
        return products.size();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder{
        private ItemProductBinding binding;
        private IRecyclerViewOnItemClick recyclerViewOnItemClick;
        public ProductViewHolder(@NonNull ItemProductBinding itemView, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.recyclerViewOnItemClick = recyclerViewOnItemClick;
        }
    }
}
