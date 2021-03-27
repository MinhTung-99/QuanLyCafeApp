package com.quanlyquancafeapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemCustomerOrderBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.CustomerOrderViewHolder>{

    private ArrayList<InvoiceDetail> invoiceDetails;
    private IRecyclerViewItemOnClick recyclerViewItemOnClick;
    public CustomerOrderAdapter(ArrayList<InvoiceDetail> invoiceDetails, IRecyclerViewItemOnClick recyclerViewItemOnClick) {
        this.invoiceDetails = invoiceDetails;
        this.recyclerViewItemOnClick = recyclerViewItemOnClick;
    }

    @NonNull
    @Override
    public CustomerOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCustomerOrderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_customer_order, parent,false);
        return new CustomerOrderViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull CustomerOrderViewHolder holder, int position) {
        holder.binding.txtNameCustomer.setText(invoiceDetails.get(position).getCustomer().getName());
        holder.binding.txtTime.setText(invoiceDetails.get(position).getTime());
        holder.itemView.setOnLongClickListener(v->{
            recyclerViewItemOnClick.onClick(invoiceDetails.get(position).getCustomer().getId());
            return false;
        });
    }
    @Override
    public int getItemCount() {
        return invoiceDetails.size();
    }
    class CustomerOrderViewHolder extends RecyclerView.ViewHolder{
        ItemCustomerOrderBinding binding;
        public CustomerOrderViewHolder(@NonNull ItemCustomerOrderBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    public interface IRecyclerViewItemOnClick{
        void onClick(Long idCustomer);
    }
}
