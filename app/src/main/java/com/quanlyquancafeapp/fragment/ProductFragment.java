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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class ProductFragment extends Fragment implements IRecyclerViewOnItemClick {
    private ProductAdapter adapter;
    private ArrayList<Product> products;
    private ArrayList<Product> productsCafe;
    private ArrayList<Product> productsDrink;
    private RecyclerView rvProduct;
    private Button btnCafe, btnDrink, btnStore;
    private boolean isCafe = true;
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

        initView();
        setBackgroundButton();
        setAdapter();
        btnCafe.setOnClickListener(v->{
            btnCafe.setBackgroundColor(getResources().getColor(R.color.blue));
            adapter.updateProduct(productsCafe);
            btnDrink.setBackgroundColor(getResources().getColor(R.color.brown));
            isCafe = true;
        });
        btnDrink.setOnClickListener(v->{
            btnDrink.setBackgroundColor(getResources().getColor(R.color.blue));
            adapter.updateProduct(productsDrink);
            btnCafe.setBackgroundColor(getResources().getColor(R.color.brown));
            isCafe = false;
        });
        btnStore.setOnClickListener(v->{
            for(int i = 0; i < productsCafe.size(); i++){
                if(productsCafe.get(i).getCount() > 0){
                    Order order = new Order();
                    order.setIdProduct(productsCafe.get(i).getId());
                    order.setCount(productsCafe.get(i).getCount());
                    if(table != null)
                        order.setIdTable(table.getId());
                    DataFake.orders.add(order);
                }
            }
            for(int i = 0; i < productsDrink.size(); i++){
                if(productsDrink.get(i).getCount() > 0){
                    Order order = new Order();
                    order.setIdProduct(productsDrink.get(i).getId());
                    order.setCount(productsDrink.get(i).getCount());
                    if(table != null)
                        order.setIdTable(table.getId());
                    DataFake.orders.add(order);
                }
            }
            if(table != null){
                DataFake.order.setIdTable(table.getId());
                Navigation.findNavController(v).popBackStack();
            }else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("table", null);
                Navigation.findNavController(v).navigate(R.id.totalMoneyFragment, bundle);
            }

        });
    }
    private void setBackgroundButton() {
        btnCafe.setBackgroundColor(getResources().getColor(R.color.blue));
        btnDrink.setBackgroundColor(getResources().getColor(R.color.brown));
        btnStore.setBackgroundColor(getResources().getColor(R.color.blue));
    }
    private void setAdapter() {
        products = DataFake.productFake();

        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                productsCafe.add(product);
            }else if(product.getSpecies().equals("DRINK")){
                productsDrink.add(product);
            }
        }
        adapter = new ProductAdapter(productsCafe, this);
        rvProduct.setAdapter(adapter);
    }
    private void initView() {
        products = new ArrayList<>();
        productsCafe = new ArrayList<>();
        productsDrink = new ArrayList<>();
        rvProduct = getView().findViewById(R.id.rv_product);
        btnCafe = getView().findViewById(R.id.btn_cafe);
        btnDrink = getView().findViewById(R.id.btn_drink);
        btnStore = getView().findViewById(R.id.btn_store);
    }
    @Override
    public void onClick(Object position) {
        handleCount(Constance.recyclerviewItem, (int) position);
    }
    @Override
    public void reductionBtn(int position) {
        handleCount(Constance.reductionBtn, position);
    }
    private void handleCount(String typeClick, int position){
        if(isCafe){
            int count = productsCafe.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                count++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                count--;
            }
            productsCafe.get(position).setCount(count);
            adapter.updateProduct(productsCafe);
        }else{
            int count = productsDrink.get(position).getCount();
            if(typeClick.equals(Constance.recyclerviewItem)){
                count++;
            }else if(typeClick.equals(Constance.reductionBtn)){
                count--;
            }
            productsDrink.get(position).setCount(count);
            adapter.updateProduct(productsDrink);
        }
    }
}
