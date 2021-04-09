package com.quanlyquancafeapp.adapter.admin;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.CustomPopupMenuBinding;
import com.quanlyquancafeapp.databinding.ItemProductAdminBinding;
import com.quanlyquancafeapp.model.Product;

import java.util.ArrayList;

public class AdminProductAdapter extends RecyclerView.Adapter<AdminProductAdapter.ProductAdminViewHolder>{
    private ArrayList<Product> products;
    private ItemProductAdminBinding binding;
    private RecyclerViewItemOnclick recyclerViewItemOnclick;
    private Context context;
    //POPUP MENU
    private PopupWindow popupWindow;
    private CustomPopupMenuBinding menuBinding;

    public AdminProductAdapter(ArrayList<Product> products, RecyclerViewItemOnclick recyclerViewItemOnclick) {
        this.products = products;
        this.recyclerViewItemOnclick = recyclerViewItemOnclick;
    }
    public void setContext(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ProductAdminViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        menuBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.custom_popup_menu, parent, false);
        popupWindow = new PopupWindow(menuBinding.getRoot(), 300, LinearLayout.LayoutParams.WRAP_CONTENT, true);

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product_admin, parent, false);
        return new ProductAdminViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductAdminViewHolder holder, int position) {
        holder.binding.setProduct(products.get(position));

        holder.binding.txtThreeDots.setOnClickListener(v->{
            popupWindow.showAsDropDown(v,-153,0);

            menuBinding.rlDelete.setOnClickListener(view->{
                popupWindow.dismiss();
                recyclerViewItemOnclick.btnDelete(products.get(position));
            });
            menuBinding.rlUpdate.setOnClickListener(view->{
                popupWindow.dismiss();
                recyclerViewItemOnclick.btnUpdate(products.get(position));
            });
        });
        holder.itemView.setOnLongClickListener(v->{
            recyclerViewItemOnclick.onLongClick(products.get(position));
            return false;
        });
    }
    public void updateProduct(ArrayList<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return products.size();
    }
    class ProductAdminViewHolder extends RecyclerView.ViewHolder{
        private ItemProductAdminBinding binding;
        public ProductAdminViewHolder(@NonNull ItemProductAdminBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }
    public interface RecyclerViewItemOnclick{
        void btnUpdate(Product product);
        void btnDelete(Product product);
        void onLongClick(Product product);
    }
}
