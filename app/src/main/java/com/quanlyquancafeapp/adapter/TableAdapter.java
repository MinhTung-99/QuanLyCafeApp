package com.quanlyquancafeapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder>{

    private ArrayList<Table> tables;
    private IRecyclerViewOnClick recyclerViewOnClick;

    public TableAdapter(ArrayList<Table> tables, IRecyclerViewOnClick recyclerViewOnClick) {
        this.tables = tables;
        this.recyclerViewOnClick = recyclerViewOnClick;
    }
    public void updateTable(ArrayList<Table> tables){
        this.tables = tables;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new TableViewHolder(view, recyclerViewOnClick);
    }
    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        holder.txtTableName.setText(tables.get(position).getTableId());
        holder.itemView.setOnClickListener(v -> holder.recyclerViewOnClick.onClick(tables.get(position)));
    }
    @Override
    public int getItemCount() {
        return tables.size();
    }
    class TableViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTableName;
        private IRecyclerViewOnClick recyclerViewOnClick;

        public TableViewHolder(@NonNull View itemView, IRecyclerViewOnClick recyclerViewOnClick) {
            super(itemView);
            txtTableName = itemView.findViewById(R.id.txt_table_id);
            this.recyclerViewOnClick = recyclerViewOnClick;
        }
    }
    public interface IRecyclerViewOnClick{
        void onClick(Table table);
    }
}