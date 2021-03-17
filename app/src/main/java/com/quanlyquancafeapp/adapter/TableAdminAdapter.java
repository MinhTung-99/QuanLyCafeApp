package com.quanlyquancafeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemTableAdminBinding;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class TableAdminAdapter extends RecyclerSwipeAdapter<TableAdminAdapter.TableAdminViewHolder> {
    private ItemTableAdminBinding binding;
    private ArrayList<Table> tables;
    private RecyclerViewItemOnClick recyclerViewItemOnClick;

    public TableAdminAdapter(ArrayList<Table> tables, RecyclerViewItemOnClick recyclerViewItemOnClick) {
        this.tables = tables;
        this.recyclerViewItemOnClick = recyclerViewItemOnClick;
    }

    @NonNull
    @Override
    public TableAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_table_admin, parent, false);
        return new TableAdminViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull TableAdminViewHolder holder, int position) {
        holder.binding.txtName.setText(tables.get(position).getName());
        mItemManger.bindView(holder.itemView, position);// open one item in swipe
        holder.binding.btnDelete.setOnClickListener(v->recyclerViewItemOnClick.btnDeleteOnClick(tables.get(position)));
    }
    public void updateTableAdmin(ArrayList<Table> tables){
        this.tables = tables;
    }
    @Override
    public int getItemCount() {
        return tables.size();
    }
    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }
    class TableAdminViewHolder extends RecyclerView.ViewHolder{
        private ItemTableAdminBinding binding;
        public TableAdminViewHolder(@NonNull ItemTableAdminBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    public interface RecyclerViewItemOnClick{
        void btnDeleteOnClick(Table table);
    }
}
