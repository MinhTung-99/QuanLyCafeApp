package com.quanlyquancafeapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.fragment.TableFragment;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;
import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableViewHolder>{

    private ArrayList<Table> tables;
    private IRecyclerViewOnItemClick recyclerViewOnItemClick;

    public TableAdapter(ArrayList<Table> tables, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.tables = tables;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }
    public void updateTable(ArrayList<Table> tables){
        this.tables = tables;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_table, parent, false);
        return new TableViewHolder(view, recyclerViewOnItemClick);
    }
    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {
        holder.txtTableName.setText(tables.get(position).getTableId());
        holder.itemView.setOnClickListener(v -> holder.recyclerViewOnClick.onClick(tables.get(position)));
        if(TableFragment.order.getIdTable() != null){
            if(TableFragment.order.getIdTable().equals(tables.get(position).getId())){
                holder.rlTop.setVisibility(View.VISIBLE);
                holder.rlBottom.setVisibility(View.GONE);
                holder.imgEmployee.setVisibility(View.VISIBLE);
            }else{
                holder.rlTop.setVisibility(View.GONE);
                holder.rlBottom.setVisibility(View.VISIBLE);
                holder.imgEmployee.setVisibility(View.GONE);
            }
        }else {
            Log.d("KMFG","NULL");
        }
    }
    @Override
    public int getItemCount() {
        return tables.size();
    }
    class TableViewHolder extends RecyclerView.ViewHolder{
        private TextView txtTableName;
        private RelativeLayout rlTop, rlBottom;
        private ImageView imgEmployee;
        private IRecyclerViewOnItemClick recyclerViewOnClick;

        public TableViewHolder(@NonNull View itemView, IRecyclerViewOnItemClick recyclerViewOnClick) {
            super(itemView);
            txtTableName = itemView.findViewById(R.id.txt_table_id);
            rlTop = itemView.findViewById(R.id.rl_top);
            rlBottom = itemView.findViewById(R.id.rl_bottom);
            imgEmployee = itemView.findViewById(R.id.img_employee);
            this.recyclerViewOnClick = recyclerViewOnClick;
        }
    }
}