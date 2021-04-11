package com.quanlyquancafeapp.adapter.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemTimeWorkBinding;
import com.quanlyquancafeapp.model.TimeWork;

import java.util.ArrayList;

public class AdminTimeWorkAdapter extends RecyclerView.Adapter<AdminTimeWorkAdapter.AdminTimeWorkViewHolder>{

    private ArrayList<TimeWork> timeWorks;

    public AdminTimeWorkAdapter(ArrayList<TimeWork> timeWorks) {
        this.timeWorks = timeWorks;
    }

    @NonNull
    @Override
    public AdminTimeWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimeWorkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_time_work, parent, false);
        return new AdminTimeWorkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTimeWorkViewHolder holder, int position) {
        holder.binding.txtTimeStart.setText(timeWorks.get(position).getTimeStart());
        holder.binding.txtTimeEnd.setText(timeWorks.get(position).getTimeEnd());
    }

    @Override
    public int getItemCount() {
        return timeWorks.size();
    }

    class AdminTimeWorkViewHolder extends RecyclerView.ViewHolder{
        private ItemTimeWorkBinding binding;
        public AdminTimeWorkViewHolder(@NonNull ItemTimeWorkBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
