package com.quanlyquancafeapp.fragment.admin.product;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminProductAdapter;
import com.quanlyquancafeapp.databinding.FragmentDrinkBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.presenter.admin.product.AdminDrinkPresenter;

import java.util.ArrayList;

public class DrinkFragment extends Fragment implements AdminProductAdapter.RecyclerViewItemOnclick{
    private FragmentDrinkBinding binding;
    private ArrayList<Product> productDrink;
    private AdminProductAdapter adapter;
    private AdminDrinkPresenter drinkPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drinkPresenter = new AdminDrinkPresenter(getContext());
        ArrayList<Product> products = drinkPresenter.getProducts();
        productDrink = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("DRINK")){
                Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
                product.setBitmap(bitmap);
                productDrink.add(product);
            }
        }
        adapter = new AdminProductAdapter(productDrink, this);
        binding.rvDrink.setAdapter(adapter);
    }
    @Override
    public void btnUpdate(Product product) {
        Toast.makeText(getContext(), "prduct", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void btnDelete() {
        Toast.makeText(getContext(), "SJDJD", Toast.LENGTH_SHORT).show();
    }
}
