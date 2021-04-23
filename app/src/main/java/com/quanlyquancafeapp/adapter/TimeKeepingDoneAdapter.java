package com.quanlyquancafeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemTimekeepingBinding;
import com.quanlyquancafeapp.model.UserTime;
import com.quanlyquancafeapp.model.UserWorking;
import com.quanlyquancafeapp.presenter.TimekeepingPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeKeepingDoneAdapter extends RecyclerView.Adapter<TimeKeepingDoneAdapter.TimekeepingViewHolder>{
    private ArrayList<UserWorking> userTimes;
    private TimekeepingPresenter presenter;
    private String date;
    private boolean isAdmin;

    public TimeKeepingDoneAdapter(ArrayList<UserWorking> userTimes, String date, Context context, boolean isAdmin) {
        this.userTimes = userTimes;
        this.date = date;
        presenter = new TimekeepingPresenter(context);
        this.isAdmin = isAdmin;
    }

    @NonNull
    @Override
    public TimekeepingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimekeepingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_timekeeping, parent, false);
        return new TimekeepingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingViewHolder holder, int position) {
        if(userTimes.get(position).getDate().equals(date)){
            holder.binding.txtTimeStart.setText(userTimes.get(position).getTimeStart()+"h");
            holder.binding.txtTimeEnd.setText(userTimes.get(position).getTimeEnd()+"h");
            holder.binding.checkbox.setChecked(true);

            holder.binding.checkbox.setOnClickListener(v->holder.binding.checkbox.setChecked(true));
        }else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return userTimes.size();
    }

    class TimekeepingViewHolder extends RecyclerView.ViewHolder{
        ItemTimekeepingBinding binding;
        public TimekeepingViewHolder(@NonNull ItemTimekeepingBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
}
