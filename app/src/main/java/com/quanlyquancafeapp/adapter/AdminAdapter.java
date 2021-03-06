package com.quanlyquancafeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemAdminBinding;
import com.quanlyquancafeapp.model.Admin;

import java.util.ArrayList;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.AdminViewHolder>{
    private ArrayList<Admin> admins;

    public AdminAdapter(ArrayList<Admin> admins) {
        this.admins = admins;
    }
    @NonNull
    @Override
    public AdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdminBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_admin,parent, false);
        return new AdminViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminViewHolder holder, int position) {
        holder.itemAdminBinding.setAdmin(admins.get(position));
    }

    @Override
    public int getItemCount() {
        return admins.size();
    }

    class AdminViewHolder extends RecyclerView.ViewHolder{
        ItemAdminBinding itemAdminBinding;
        public AdminViewHolder(@NonNull ItemAdminBinding itemAdminBinding) {
            super(itemAdminBinding.getRoot());
            this.itemAdminBinding = itemAdminBinding;
        }
    }
}
