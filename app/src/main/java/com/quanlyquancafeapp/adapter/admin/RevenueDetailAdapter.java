package com.quanlyquancafeapp.adapter.admin;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemRevenueDetailBinding;
import com.quanlyquancafeapp.model.InvoiceDetail;
import java.util.ArrayList;

public class RevenueDetailAdapter extends RecyclerView.Adapter<RevenueDetailAdapter.RevenueDetailViewHolder>{

    ArrayList<InvoiceDetail> invoiceDetails;

    public RevenueDetailAdapter(ArrayList<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    @NonNull
    @Override
    public RevenueDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRevenueDetailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_revenue_detail, parent, false);
        return new RevenueDetailViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull RevenueDetailViewHolder holder, int position) {
        convertImageByteArrToBitmap(position);
        holder.binding.imgProduct.setImageBitmap(invoiceDetails.get(position).getProduct().getImageBitmap());
        holder.binding.txtNameProduct.setText(invoiceDetails.get(position).getProduct().getName());
        holder.binding.txtCount.setText("bán " + invoiceDetails.get(position).getCount());
    }
    private void convertImageByteArrToBitmap(int position){
        Bitmap bitmap = BitmapFactory.decodeByteArray(invoiceDetails.get(position).getProduct().getImageByteArr(), 0, invoiceDetails.get(position).getProduct().getImageByteArr().length);
        invoiceDetails.get(position).getProduct().setImageBitmap(bitmap);
    }
    @Override
    public int getItemCount() {
        return invoiceDetails.size();
    }
    class RevenueDetailViewHolder extends RecyclerView.ViewHolder{
        ItemRevenueDetailBinding binding;
        public RevenueDetailViewHolder(@NonNull ItemRevenueDetailBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
