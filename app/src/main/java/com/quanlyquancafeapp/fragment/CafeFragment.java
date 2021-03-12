package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.adapter.ProductAdminAdapter;
import com.quanlyquancafeapp.databinding.FragmentCafeBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.utils.DataFake;

import java.util.ArrayList;

public class CafeFragment extends Fragment {
    private FragmentCafeBinding binding;
    private ArrayList<Product> productCafe;
    private ProductAdminAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cafe, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Product> products = DataFake.productFake();
        productCafe = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                productCafe.add(product);
            }
        }
        adapter = new ProductAdminAdapter(productCafe);
        binding.rvCafe.setAdapter(adapter);
    }
}
