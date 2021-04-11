package com.quanlyquancafeapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemCustomerOrderBottomSheetBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;

import java.util.ArrayList;

public class CustomerOrderBottomSheetAdapter extends RecyclerView.Adapter<CustomerOrderBottomSheetAdapter.CustomerOrderBottomSheetViewHolder>{

    private ArrayList<InvoiceDetail> invoiceDetails;

    public CustomerOrderBottomSheetAdapter(ArrayList<InvoiceDetail> invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    @NonNull
    @Override
    public CustomerOrderBottomSheetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCustomerOrderBottomSheetBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_customer_order_bottom_sheet,parent, false);
        return new CustomerOrderBottomSheetViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOrderBottomSheetViewHolder holder, int position) {
        convertImageByteArrToBitmap(position);
        holder.binding.imgProduct.setImageBitmap(invoiceDetails.get(position).getProduct().getImageBitmap());
        holder.binding.txtNameAndCountProduct.setText(invoiceDetails.get(position).getProduct().getName() + " ----- " +
                invoiceDetails.get(position).getCount() + " cốc");
        holder.binding.txtDescription.setText(invoiceDetails.get(position).getDescription());

    }
    private void convertImageByteArrToBitmap(int position){
        Bitmap bitmap = BitmapFactory.decodeByteArray(invoiceDetails.get(position).getProduct().getImageByteArr(), 0, invoiceDetails.get(position).getProduct().getImageByteArr().length);
        invoiceDetails.get(position).getProduct().setImageBitmap(bitmap);
    }
    @Override
    public int getItemCount() {
        return invoiceDetails.size();
    }

    class CustomerOrderBottomSheetViewHolder extends RecyclerView.ViewHolder{
        ItemCustomerOrderBottomSheetBinding binding;
        public CustomerOrderBottomSheetViewHolder(@NonNull ItemCustomerOrderBottomSheetBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
