package com.quanlyquancafeapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemCustomerOrderBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerSwipeAdapter<CustomerOrderAdapter.CustomerOrderViewHolder> {

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

        mItemManger.bindView(holder.itemView, position);

        if(invoiceDetails.get(position).getIsPay() == 0){
            holder.binding.txtNameCustomer.setText(invoiceDetails.get(position).getCustomer().getName());
            holder.binding.txtTime.setText(invoiceDetails.get(position).getTime());
            holder.itemView.setOnLongClickListener(v->{
                recyclerViewItemOnClick.onClick(invoiceDetails.get(position).getCustomer().getId());
                return false;
            });
            holder.binding.btnTotalMoney.setOnClickListener(v->recyclerViewItemOnClick.btnTotalMoney(invoiceDetails.get(position)));
        }else {
            holder.binding.txtNameCustomer.setText(invoiceDetails.get(position).getCustomer().getName());
            holder.binding.txtTime.setText(invoiceDetails.get(position).getTime());
            holder.binding.btnTotalMoney.setText("Đã thanh toán");
            holder.binding.btnTotalMoney.setEnabled(false);
        }

        holder.binding.imgUpdate.setOnClickListener(v->{
            recyclerViewItemOnClick.imgUpdate(
                    invoiceDetails.get(position).getCustomer().getId(),
                    invoiceDetails.get(position).getIdInvoice(),
                    invoiceDetails.get(position).getIdTable()
            );
        });
    }
    @Override
    public int getItemCount() {
        return invoiceDetails.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
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
        void btnTotalMoney(InvoiceDetail invoiceDetail);
        void imgUpdate(Long idCustomer, Long idInvoice, Long idTable);
    }
}
