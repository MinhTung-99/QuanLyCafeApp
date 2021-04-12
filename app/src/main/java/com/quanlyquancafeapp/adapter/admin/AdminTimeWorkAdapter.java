package com.quanlyquancafeapp.adapter.admin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.CustomPopupMenuBinding;
import com.quanlyquancafeapp.databinding.ItemTimeWorkBinding;
import com.quanlyquancafeapp.model.TimeWork;

import java.util.ArrayList;

public class AdminTimeWorkAdapter extends RecyclerView.Adapter<AdminTimeWorkAdapter.AdminTimeWorkViewHolder>{

    private ArrayList<TimeWork> timeWorks;
    private IRecyclerViewItemOnclick recyclerViewItemOnclick;
    //MENU
    private PopupWindow popupWindow;
    private CustomPopupMenuBinding menuBinding;

    public AdminTimeWorkAdapter(ArrayList<TimeWork> timeWorks, IRecyclerViewItemOnclick recyclerViewItemOnclick) {
        this.timeWorks = timeWorks;
        this.recyclerViewItemOnclick = recyclerViewItemOnclick;
    }

    @NonNull
    @Override
    public AdminTimeWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        menuBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_popup_menu, parent, false);
        popupWindow = new PopupWindow(menuBinding.getRoot(), 300, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        ItemTimeWorkBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_time_work, parent, false);
        return new AdminTimeWorkViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTimeWorkViewHolder holder, int position) {
        holder.binding.txtTimeStart.setText(timeWorks.get(position).getTimeStart()+"h");
        holder.binding.txtTimeEnd.setText(timeWorks.get(position).getTimeEnd()+"h");

        holder.binding.txtThreeDots.setOnClickListener(v->{
            popupWindow.showAsDropDown(v,-153,0);

            menuBinding.rlDelete.setOnClickListener(view->{
                recyclerViewItemOnclick.menuDelete(timeWorks.get(position));
                popupWindow.dismiss();
            });
            menuBinding.rlUpdate.setOnClickListener(view->{
                recyclerViewItemOnclick.menuUpdate(timeWorks.get(position));
                popupWindow.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        return timeWorks.size();
    }

    public void updateTimeWork(ArrayList<TimeWork> timeWorks){
        this.timeWorks = timeWorks;
        notifyDataSetChanged();
    }

    class AdminTimeWorkViewHolder extends RecyclerView.ViewHolder{
        private ItemTimeWorkBinding binding;
        public AdminTimeWorkViewHolder(@NonNull ItemTimeWorkBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    public interface IRecyclerViewItemOnclick{
        void menuUpdate(TimeWork timeWork);
        void menuDelete(TimeWork timeWork);
    }
}
