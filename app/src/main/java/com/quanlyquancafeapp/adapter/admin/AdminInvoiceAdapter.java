package com.quanlyquancafeapp.adapter.admin;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemAdminInvoiceBinding;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class AdminInvoiceAdapter extends RecyclerView.Adapter<AdminInvoiceAdapter.InvoiceViewHolder>{
    private ArrayList<Invoice> invoices;
    private IRecyclerViewOnItemClick recyclerViewOnItemClick;

    public AdminInvoiceAdapter(ArrayList<Invoice> invoices, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.invoices = invoices;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }
    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdminInvoiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_admin_invoice, parent, false);
        return new InvoiceViewHolder(binding, recyclerViewOnItemClick);
    }
    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        holder.binding.txtTime.setText(invoices.get(position).getDateBuy() + "-----" + invoices.get(position).getTime());
        holder.binding.getRoot().setOnClickListener(v->recyclerViewOnItemClick.onClick(invoices.get(position)));
    }
    @Override
    public int getItemCount() {
        return invoices.size();
    }
    class InvoiceViewHolder extends RecyclerView.ViewHolder{
        private ItemAdminInvoiceBinding binding;
        public InvoiceViewHolder(@NonNull ItemAdminInvoiceBinding itemView, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
