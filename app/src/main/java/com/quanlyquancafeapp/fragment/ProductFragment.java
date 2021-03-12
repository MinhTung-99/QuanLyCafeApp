package com.quanlyquancafeapp.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
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
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.ScanCodeActivity;
import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.databinding.FragmentProductBinding;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class ProductFragment extends Fragment implements IRecyclerViewOnItemClick {
    private FragmentProductBinding binding;
    private ProductAdapter adapter;
    private ArrayList<Product> products;
    private ArrayList<Product> productsCafe;
    private ArrayList<Product> productsDrink;
    private boolean isCafe = true;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false);
        return binding.getRoot();
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Table table = (Table) getArguments().getSerializable("table");

        productsCafe = new ArrayList<>();
        productsDrink = new ArrayList<>();
        setBackgroundButton();
        setAdapter();
        binding.btnCafe.setOnClickListener(v->{
            binding.btnCafe.setBackgroundColor(getResources().getColor(R.color.blue));
            adapter.updateProduct(productsCafe);
            binding.btnDrink.setBackgroundColor(getResources().getColor(R.color.brown));
            isCafe = true;
        });
        binding.btnDrink.setOnClickListener(v->{
            binding.btnDrink.setBackgroundColor(getResources().getColor(R.color.blue));
            adapter.updateProduct(productsDrink);
            binding.btnCafe.setBackgroundColor(getResources().getColor(R.color.brown));
            isCafe = false;
        });
        binding.btnStore.setOnClickListener(v->{
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
        binding.imgQrCode.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), ScanCodeActivity.class);
            startActivity(intent);
        });
    }
    private void setBackgroundButton() {
        binding.btnCafe.setBackgroundColor(getResources().getColor(R.color.blue));
        binding.btnDrink.setBackgroundColor(getResources().getColor(R.color.brown));
        binding.btnStore.setBackgroundColor(getResources().getColor(R.color.blue));
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
        binding.rvProduct.setAdapter(adapter);
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
