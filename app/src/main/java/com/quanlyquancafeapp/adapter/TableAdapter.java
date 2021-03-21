package com.quanlyquancafeapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemTableBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.fragment.TableFragment;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;
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
        holder.itemView.setOnClickListener(v -> recyclerViewItemOnClick.onClick(position));

        holder.tableBinding.rlTop.setVisibility(View.GONE);
        holder.tableBinding.rlBottom.setVisibility(View.VISIBLE);
        holder.tableBinding.imgEmployee.setVisibility(View.GONE);

        DatabaseHelper db = new DatabaseHelper(context);
        for(int i = 0; i < db.getDetailInvoices().size(); i++){
            if(db.getDetailInvoices().get(i).getIdTable() == tables.get(position).getId()){
                holder.tableBinding.rlTop.setVisibility(View.VISIBLE);
                holder.tableBinding.rlBottom.setVisibility(View.GONE);
                holder.tableBinding.imgEmployee.setVisibility(View.VISIBLE);
            }
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