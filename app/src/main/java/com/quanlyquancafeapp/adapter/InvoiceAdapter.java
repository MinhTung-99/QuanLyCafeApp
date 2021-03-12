package com.quanlyquancafeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemInvoiceBinding;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>{
    private ArrayList<Invoice> invoices;
    private IRecyclerViewOnItemClick recyclerViewOnItemClick;
    public InvoiceAdapter(ArrayList<Invoice> invoices, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.invoices = invoices;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }
    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInvoiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_invoice, parent, false);
        return new InvoiceViewHolder(binding, recyclerViewOnItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder holder, int position) {
        holder.binding.setInvoice(invoices.get(position));
        holder.binding.getRoot().setOnClickListener(v->recyclerViewOnItemClick.onClick(invoices.get(position)));
    }

    @Override
    public int getItemCount() {
        return invoices.size();
    }

    class InvoiceViewHolder extends RecyclerView.ViewHolder{
        private ItemInvoiceBinding binding;
        private IRecyclerViewOnItemClick recyclerViewOnItemClick;
        public InvoiceViewHolder(@NonNull ItemInvoiceBinding itemView, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.recyclerViewOnItemClick = recyclerViewOnItemClick;
        }
    }
}
