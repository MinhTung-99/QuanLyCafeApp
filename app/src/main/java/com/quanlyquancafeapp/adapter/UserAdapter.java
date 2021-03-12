package com.quanlyquancafeapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.DialogAddOrFixBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteBinding;
import com.quanlyquancafeapp.databinding.ItemUserBinding;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class UserAdapter extends RecyclerSwipeAdapter<UserAdapter.UserViewHolder>{
    private ArrayList<User> users;
    private DialogDeleteBinding dialogDeleteBinding;
    private AlertDialog alertDialog;
    private Context context;
    private IRecyclerViewOnItemClick recyclerViewOnItemClick;

    public UserAdapter(ArrayList<User> users, Context context, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.users = users;
        this.context = context;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        dialogDeleteBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.dialog_delete, parent,false);
        initDialog();
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new UserViewHolder(binding, recyclerViewOnItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));
        holder.binding.btnDelete.setOnClickListener(v->{
            Log.d("KMFG", "OOOOOP");
            alertDialog.show();
        });
        dialogDeleteBinding.txtToolbar.setText("Xoá nhân viên");
        holder.binding.getRoot().setOnLongClickListener(v -> {
            recyclerViewOnItemClick.onClick(users.get(position));
            return false;
        });
        mItemManger.bindView(holder.itemView, position);// open one item in swipe
    }
    private void initDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogDeleteBinding.getRoot());
        alertDialog = dialogBuilder.create();
    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        private ItemUserBinding binding;
        private IRecyclerViewOnItemClick recyclerViewOnItemClick;
        public UserViewHolder(@NonNull ItemUserBinding itemView, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.recyclerViewOnItemClick = recyclerViewOnItemClick;
        }
    }
}
