package com.quanlyquancafeapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.fragment.TableFragment;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.PriceUtil;

import java.util.ArrayList;

public class TotalMoneyAdapter extends RecyclerView.Adapter<TotalMoneyAdapter.TotalMoneyViewHolder>{
    private ArrayList<Order> orders;

    public TotalMoneyAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public TotalMoneyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_total_money, parent, false);
        return new TotalMoneyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TotalMoneyViewHolder holder, int position) {
        holder.txtCount.setText(String.valueOf(orders.get(position).getCount()));

        ArrayList<Product> products = DataFake.productFake();
        for(int i = 0; i < products.size(); i++){
            if(DataFake.orders.get(position).getIdProduct() == products.get(i).getId()){
                holder.txtProduct.setText(String.valueOf(products.get(i).getName()));
                holder.txtSale.setText("("+products.get(i).getSale()+")");
                if(products.get(i).getSale().equals("")){
                    holder.txtSale.setVisibility(View.GONE);
                }else {
                    holder.txtSale.setVisibility(View.VISIBLE);
                }
            }
        }
        String setupPrice = PriceUtil.setupPrice(String.valueOf(orders.get(position).getTotalMoney()));
        holder.txtIntoMoney.setText(setupPrice);
    }
    @Override
    public int getItemCount() {
        return orders.size();
    }
    class TotalMoneyViewHolder extends RecyclerView.ViewHolder{
        private TextView txtCount;
        private TextView txtProduct;
        private TextView txtSale;
        private TextView txtIntoMoney;
        public TotalMoneyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCount = itemView.findViewById(R.id.txt_count);
            txtProduct = itemView.findViewById(R.id.txt_product);
            txtSale = itemView.findViewById(R.id.txt_sale);
            txtIntoMoney = itemView.findViewById(R.id.txt_intoMoney);
        }
    }
}
