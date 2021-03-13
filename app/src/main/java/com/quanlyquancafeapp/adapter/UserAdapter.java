package com.quanlyquancafeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.ItemUserBinding;
import com.quanlyquancafeapp.model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerSwipeAdapter<UserAdapter.UserViewHolder>{
    private ArrayList<User> users;
    private Context context;
    private IRecyclerViewOnClick recyclerViewOnClick;

    public UserAdapter(ArrayList<User> users, Context context, IRecyclerViewOnClick recyclerViewOnClick) {
        this.users = users;
        this.context = context;
        this.recyclerViewOnClick = recyclerViewOnClick;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_user, parent, false);
        return new UserViewHolder(binding, recyclerViewOnClick);
    }
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));
        holder.binding.btnDelete.setOnClickListener(v->{
            recyclerViewOnClick.btnDeleteOnClick(position);
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
        private ItemUserBinding binding;
        private IRecyclerViewOnClick recyclerViewOnItemClick;
        public UserViewHolder(@NonNull ItemUserBinding itemView, IRecyclerViewOnClick recyclerViewOnClick) {
            super(itemView.getRoot());
            this.binding = itemView;
            this.recyclerViewOnItemClick = recyclerViewOnClick;
        }
    }
    public interface IRecyclerViewOnClick{
        void onItemLongClick(User user);
        void btnDeleteOnClick(int position);
    }
}
