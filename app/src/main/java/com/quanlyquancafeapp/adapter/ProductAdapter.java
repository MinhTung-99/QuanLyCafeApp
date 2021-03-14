package com.quanlyquancafeapp.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.fragment.TableFragment;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;
import com.quanlyquancafeapp.utils.PriceUtil;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private ArrayList<Product> products;
    private IRecyclerViewOnItemClick recyclerViewOnItemClick;

    public ProductAdapter(ArrayList<Product> products, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
        this.products = products;
        this.recyclerViewOnItemClick = recyclerViewOnItemClick;
    }
    public void updateProduct(ArrayList<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view, recyclerViewOnItemClick);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(products.get(position).getImage(), 0, products.get(position).getImage().length);
        products.get(position).setBitmap(bitmap);
        holder.imgProduct.setImageBitmap(products.get(position).getBitmap());
        holder.txtName.setText(products.get(position).getName() + " - ");
        String setupPrice = PriceUtil.setupPrice(String.valueOf(products.get(position).getPrice()));
        String setupPriceByComma = PriceUtil.getPriceByComma(setupPrice);
        holder.txtPrice.setText(setupPriceByComma+"k");
        holder.txtCount.setText(String.valueOf(products.get(position).getCount()));
        holder.itemView.setOnClickListener(v->holder.recyclerViewOnItemClick.onClick(position));
        holder.btnReduction.setOnClickListener(v->holder.recyclerViewOnItemClick.reductionBtn(position));
        if(products.get(position).getCount() == 0){
            holder.btnReduction.setVisibility(View.GONE);
            holder.txtCount.setVisibility(View.GONE);
        }else {
            holder.btnReduction.setVisibility(View.VISIBLE);
            holder.txtCount.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public int getItemCount() {
        return products.size();
    }
    class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView txtName, txtPrice, txtCount;
        private Button btnReduction;
        private IRecyclerViewOnItemClick recyclerViewOnItemClick;
        public ProductViewHolder(@NonNull View itemView, IRecyclerViewOnItemClick recyclerViewOnItemClick) {
            super(itemView);

            imgProduct = itemView.findViewById(R.id.img_product);
            txtName = itemView.findViewById(R.id.txt_name);
            txtPrice = itemView.findViewById(R.id.txt_price);
            txtCount = itemView.findViewById(R.id.txt_count);
            btnReduction = itemView.findViewById(R.id.btn_reduction);
            this.recyclerViewOnItemClick = recyclerViewOnItemClick;
        }
    }
}
