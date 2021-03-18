package com.quanlyquancafeapp.adapter.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemAdminUserBinding;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class AdminUserAdapter extends RecyclerSwipeAdapter<AdminUserAdapter.UserViewHolder>{
    private ArrayList<User> users;
    private Context context;
    private IRecyclerViewOnClick recyclerViewOnClick;

    public AdminUserAdapter(ArrayList<User> users, Context context, IRecyclerViewOnClick recyclerViewOnClick) {
        this.users = users;
        this.context = context;
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
        holder.binding.btnDelete.setOnClickListener(v->{
            recyclerViewOnClick.btnDeleteOnClick(users.get(position));
        });
        holder.binding.getRoot().setOnLongClickListener(v -> {
            recyclerViewOnClick.onItemLongClick(users.get(position));
            return false;
        });
        mItemManger.bindView(holder.itemView, position);// open one item in swipe
    }
    public void updateUser(ArrayList<User> users){
        this.users = users;
        notifyDataSetChanged();
    }
    public void closeSwipe(){
        mItemManger.closeAllItems();
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
        private ItemAdminUserBinding binding;
        public UserViewHolder(@NonNull ItemAdminUserBinding itemView, IRecyclerViewOnClick recyclerViewOnClick) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    public interface IRecyclerViewOnClick{
        void onItemLongClick(User user);
        void btnDeleteOnClick(User user);
    }
}
