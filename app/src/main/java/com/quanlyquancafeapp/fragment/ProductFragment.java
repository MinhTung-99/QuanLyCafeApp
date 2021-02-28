package com.quanlyquancafeapp.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class ProductFragment extends Fragment {
    private ProductAdapter adapter;
    private ArrayList<Product> products;
    private ArrayList<Product> productsCafe;
    private ArrayList<Product> productsDrink;
    private RecyclerView rvProduct;
    private Button btnCafe, btnDrink;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Table table = (Table) getArguments().getSerializable("table");
        Toast.makeText(getContext(), table.getName(), Toast.LENGTH_SHORT).show();

        initView();
        setAdapter();
        btnCafe.setOnClickListener(v->{
            btnCafe.setBackgroundColor(getResources().getColor(R.color.blue));
            adapter.updateProduct(productsCafe);
            btnDrink.setBackgroundColor(getResources().getColor(R.color.brown));
        });
        btnDrink.setOnClickListener(v->{
            btnDrink.setBackgroundColor(getResources().getColor(R.color.blue));
            adapter.updateProduct(productsDrink);
            btnCafe.setBackgroundColor(getResources().getColor(R.color.brown));
        });
    }
    private void setAdapter() {
        products.add(new Product("cafe đen đá",R.drawable.ic_cafe_den_da,37000,"CAFE"));
        products.add(new Product("cafe sữa",R.drawable.ic_cafe_milk,27000,"CAFE"));
        products.add(new Product("7 up", R.drawable.ic_7up, 15000,"DRINK"));
        products.add(new Product("coca", R.drawable.ic_coca, 15000,"DRINK"));
        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                productsCafe.add(product);
            }else if(product.getSpecies().equals("DRINK")){
                productsDrink.add(product);
            }
        }
        adapter = new ProductAdapter(productsCafe);
        rvProduct.setAdapter(adapter);
    }
    private void initView() {
        products = new ArrayList<>();
        productsCafe = new ArrayList<>();
        productsDrink = new ArrayList<>();
        rvProduct = getView().findViewById(R.id.rv_product);
        btnCafe = getView().findViewById(R.id.btn_cafe);
        btnDrink = getView().findViewById(R.id.btn_drink);
    }
}
