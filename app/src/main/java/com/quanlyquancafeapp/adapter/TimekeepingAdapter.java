package com.quanlyquancafeapp.adapter;

import android.content.Context;
import android.util.Log;
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

public class TimekeepingAdapter extends RecyclerView.Adapter<TimekeepingAdapter.TimekeepingViewHolder>{
    private ArrayList<UserTime> userTimes;
    private TimekeepingPresenter presenter;

    public TimekeepingAdapter(ArrayList<UserTime> userTimes, Context context) {
        this.userTimes = userTimes;
        presenter = new TimekeepingPresenter(context);
    }

    @NonNull
    @Override
    public TimekeepingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTimekeepingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_timekeeping, parent, false);
        return new TimekeepingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimekeepingViewHolder holder, int position) {
        holder.binding.txtTimeStart.setText(userTimes.get(position).getTimeStart()+"h");
        holder.binding.txtTimeEnd.setText(userTimes.get(position).getTimeEnd()+"h");

        SimpleDateFormat getDate = new SimpleDateFormat("d/M/yyyy");
        Date date = new Date();

        boolean isChecked = false;
        ArrayList<UserWorking> userWorkings = presenter.getUserWorking();
        for(UserWorking userWorking: userWorkings){
            if(Integer.parseInt(userWorking.getTimeStart()) - Integer.parseInt(userTimes.get(position).getTimeStart()) == 0
                && userWorking.getDate().equals(getDate.format(date))){
                holder.binding.checkbox.setChecked(true);
                isChecked = true;
                break;
            }
        }

        final boolean finalIsChecked = isChecked;
        holder.binding.checkbox.setOnClickListener(v->{
            if(!finalIsChecked){
                UserWorking userWorking = new UserWorking();
                userWorking.setDate(getDate.format(date));
                userWorking.setIdUser(userTimes.get(position).getIdUserTime());
                userWorking.setTimeStart(userTimes.get(position).getTimeStart());
                userWorking.setTimeEnd(userTimes.get(position).getTimeEnd());
                presenter.addUserTimeWorking(userWorking);
            }else {
                holder.binding.checkbox.setChecked(true);
            }
        });
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
