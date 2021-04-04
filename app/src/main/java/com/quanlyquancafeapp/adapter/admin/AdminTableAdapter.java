package com.quanlyquancafeapp.adapter.admin;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemAdminTableBinding;
import com.quanlyquancafeapp.model.Table;
import java.util.ArrayList;

public class AdminTableAdapter extends RecyclerSwipeAdapter<AdminTableAdapter.TableAdminViewHolder> {
    private ItemAdminTableBinding binding;
    private ArrayList<Table> tables;
    private RecyclerViewItemOnClick recyclerViewItemOnClick;

    public AdminTableAdapter(ArrayList<Table> tables, RecyclerViewItemOnClick recyclerViewItemOnClick) {
        this.tables = tables;
        this.recyclerViewItemOnClick = recyclerViewItemOnClick;
    }

    @NonNull
    @Override
    public TableAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_admin_table, parent, false);
        return new TableAdminViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull TableAdminViewHolder holder, int position) {
        holder.binding.txtName.setText(tables.get(position).getName()+"----"+tables.get(position).getCountPeople()+" người");

        mItemManger.bindView(holder.itemView, position);// open one item in swipe
        holder.binding.btnDelete.setOnClickListener(v->recyclerViewItemOnClick.btnDeleteOnClick(tables.get(position)));
        holder.binding.getRoot().setOnLongClickListener(v -> {
            recyclerViewItemOnClick.onItemLongClick(tables.get(position));
            return false;
        });
    }
    public void updateTableAdmin(ArrayList<Table> tables){
        this.tables = tables;
    }
    public void closeAllSwipe(){
        mItemManger.closeAllItems();
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
        private ItemAdminTableBinding binding;
        public TableAdminViewHolder(@NonNull ItemAdminTableBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    public interface RecyclerViewItemOnClick{
        void onItemLongClick(Table table);
        void btnDeleteOnClick(Table table);
    }
}
