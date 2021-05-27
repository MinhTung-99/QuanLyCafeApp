package com.quanlyquancafeapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.TimekeepingActivity;
import com.quanlyquancafeapp.databinding.ItemTimekeepingBinding;
import com.quanlyquancafeapp.model.Admin;
import com.quanlyquancafeapp.model.UserTime;
import com.quanlyquancafeapp.model.UserWorking;
import com.quanlyquancafeapp.presenter.TimekeepingPresenter;
import com.quanlyquancafeapp.presenter.admin.AdminTimeWorkPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimekeepingAdapter extends RecyclerView.Adapter<TimekeepingAdapter.TimekeepingViewHolder>{
    private ArrayList<UserTime> userTimes;
    private TimekeepingPresenter presenter;
    private String date;
    private AdminTimeWorkPresenter timeWorkPresenter;
    private Activity activity;

    public TimekeepingAdapter(ArrayList<UserTime> userTimes, String date,Context context, Activity activity) {
        this.userTimes = userTimes;
        this.date = date;
        presenter = new TimekeepingPresenter(context);
        timeWorkPresenter = new AdminTimeWorkPresenter();
        this.activity = activity;
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

        SimpleDateFormat getTime = new SimpleDateFormat("HH:mm");
        Date time = new Date();

        boolean isChecked = false;
        ArrayList<UserWorking> userWorkings = presenter.getUserWorking();
        for(UserWorking userWorking: userWorkings){
            if(Integer.parseInt(userWorking.getTimeStart().substring(0,2)) - Integer.parseInt(userTimes.get(position).getTimeStart().substring(0,2)) == 0
                && userWorking.getDate().equals(date)
                && userWorking.getIdUser().equals(userTimes.get(position).getId())
                && Integer.parseInt(userWorking.getTimeStart().substring(3,5)) - Integer.parseInt(userTimes.get(position).getTimeStart().substring(3,5)) <= 15){
                holder.binding.checkbox.setChecked(true);

                isChecked = true;
                break;
            }
        }

        final boolean finalIsChecked = isChecked;
        holder.binding.checkbox.setOnClickListener(v->{
            if(!finalIsChecked){

                String[] myTime = getTime.format(time).split(":");

                if(Integer.parseInt(myTime[0]) - Integer.parseInt(userTimes.get(position).getTimeStart().substring(0,2)) == 0
                        && Integer.parseInt(myTime[1]) - Integer.parseInt(userTimes.get(position).getTimeStart().substring(3,5)) <= 15){

                    UserWorking userWorking = new UserWorking();
                    userWorking.setDate(date);
                    userWorking.setIdUser(userTimes.get(position).getId());
                    userWorking.setTimeStart(getTime.format(time));
                    userWorking.setTimeEnd(userTimes.get(position).getTimeEnd());
                    presenter.addUserTimeWorking(userWorking);

                    activity.finish();

                }else {
                    holder.binding.checkbox.setChecked(false);
                }
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
