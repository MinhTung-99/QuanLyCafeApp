package com.quanlyquancafeapp.adapter.admin;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemAdminUserBinding;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.UserViewHolder>{
    private ArrayList<User> users;
    private IRecyclerViewOnClick recyclerViewOnClick;

    public AdminUserAdapter(ArrayList<User> users, IRecyclerViewOnClick recyclerViewOnClick) {
        this.users = users;
        this.recyclerViewOnClick = recyclerViewOnClick;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemAdminUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_admin_user, parent, false);
        return new UserViewHolder(binding, recyclerViewOnClick);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));
        holder.binding.imgProfile.setImageBitmap(BitmapFactory.decodeFile(users.get(position).getFilePath()));

        holder.binding.btnDelete.setOnClickListener(v-> recyclerViewOnClick.btnDeleteOnClick(users.get(position)) );

        holder.binding.getRoot().setOnLongClickListener(v -> {
            recyclerViewOnClick.onItemLongClick(users.get(position));
            return false;
        });

        holder.binding.btnTimer.setOnClickListener(v->recyclerViewOnClick.btnTimerOnClick(users.get(position)));

        holder.binding.imgEye.setOnClickListener(v-> recyclerViewOnClick.imgEyeOnClick(users.get(position)));
    }
    public void updateUser(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return users.size();
    }
    class UserViewHolder extends RecyclerView.ViewHolder{
        private ItemAdminUserBinding binding;
        public UserViewHolder(@NonNull ItemAdminUserBinding itemView, IRecyclerViewOnClick recyclerViewOnClick) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    public interface IRecyclerViewOnClick{
        void onItemLongClick(User user);
        void btnDeleteOnClick(User user);
        void btnTimerOnClick(User user);
        void imgEyeOnClick(User user);
    }
}
