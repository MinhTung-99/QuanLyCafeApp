package com.quanlyquancafeapp.adapter.admin;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemAdminProductInvoiceBinding;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.utils.PriceUtil;

import java.util.ArrayList;

public class AdminProductInvoiceAdapter extends RecyclerView.Adapter<AdminProductInvoiceAdapter.AdminProductInvoiceHolder>{
    private ArrayList<InvoiceDetail> invoiceDetails;
    private ArrayList<Float> totalMoney;

    public AdminProductInvoiceAdapter(ArrayList<InvoiceDetail> invoiceDetails, ArrayList<Float> totalMoney) {
        this.invoiceDetails = invoiceDetails;
        this.totalMoney = totalMoney;
    }

    @NonNull
    @Override
    public AdminProductInvoiceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdminProductInvoiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_admin_product_invoice,parent, false);
        return new AdminProductInvoiceHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull AdminProductInvoiceHolder holder, int position) {
        holder.binding.txtNumericalOrder.setText(String.valueOf(position+1));
        holder.binding.txtSale.setText(invoiceDetails.get(position).getSale()+"%");
        holder.binding.txtName.setText(invoiceDetails.get(position).getProduct().getName());
        holder.binding.txtPrice.setText(PriceUtil.setupPrice(String.valueOf(totalMoney.get(position))));
        holder.binding.txtCount.setText(String.valueOf(invoiceDetails.get(position).getCount()));
    }
    @Override
    public int getItemCount() {
        return invoiceDetails.size();
    }
    class AdminProductInvoiceHolder extends RecyclerView.ViewHolder{
        private ItemAdminProductInvoiceBinding binding;
        public AdminProductInvoiceHolder(@NonNull ItemAdminProductInvoiceBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
