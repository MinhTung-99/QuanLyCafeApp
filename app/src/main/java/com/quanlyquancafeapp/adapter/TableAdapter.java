package com.quanlyquancafeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemTableBinding;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder>{
    private ArrayList<Table> tables;
    private RecyclerViewItemOnClick recyclerViewItemOnClick;
    private Context context;

    public TableAdapter(ArrayList<Table> tables, RecyclerViewItemOnClick recyclerViewOnItemClick, Context context) {
        this.tables = tables;
        this.recyclerViewItemOnClick = recyclerViewOnItemClick;
        this.context = context;
    }
    public void updateTable(ArrayList<Table> tables){
        this.tables = tables;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTableBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_table, parent, false);
        return new TableViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        holder.tableBinding.txtTableName.setText(tables.get(position).getName());
        holder.itemView.setOnClickListener(v ->{
            if(tables.get(position).getCountCurrentPeople() != tables.get(position).getCountPeople()){
                recyclerViewItemOnClick.onClick(position);
            }
        });
        if(tables.get(position).getCountCurrentPeople() == 0){
            holder.tableBinding.txtCountCurrentPeople.setText("0/"+tables.get(position).getCountPeople());
        }else if(tables.get(position).getCountCurrentPeople() == tables.get(position).getCountPeople()){
            holder.tableBinding.txtCountCurrentPeople.setText("FULL");
            holder.tableBinding.rlBottom.setBackground(context.getDrawable(R.drawable.rounded_red));
        } else {
            holder.tableBinding.txtCountCurrentPeople.setText(tables.get(position).getCountCurrentPeople()+"/"+tables.get(position).getCountPeople());
        }
    }
    @Override
    public int getItemCount() {
        return tables.size();
    }
    class TableViewHolder extends RecyclerView.ViewHolder{
        private ItemTableBinding tableBinding;

        public TableViewHolder(@NonNull ItemTableBinding itemView) {
            super(itemView.getRoot());
            this.tableBinding = itemView;
        }
    }
    public interface RecyclerViewItemOnClick{
        void onClick(int position);
    }
}