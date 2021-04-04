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
import com.quanlyquancafeapp.CustomerOrderActivity;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.ScanCodeActivity;
import com.quanlyquancafeapp.adapter.ProductAdapter;
import com.quanlyquancafeapp.databinding.FragmentProductBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.ProductPresenter;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;
import com.quanlyquancafeapp.view.IProductView;

import java.util.ArrayList;

public class ProductFragment extends Fragment implements View.OnClickListener, IRecyclerViewOnItemClick, IProductView {
    private FragmentProductBinding binding;
    private ProductAdapter adapter;
    private ArrayList<Product> productsCafe;
    private ArrayList<Product> productsDrink;
    private boolean isCafe = true;
    private ProductPresenter productPresenter;
    private Table table;
    DatabaseHelper db;

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
        table = (Table) getArguments().getSerializable("table");
        db = new DatabaseHelper(getContext());
        init();
        initAction();
        setFirstBackgroundButton();
        setAdapter();
    }
    private void initAction() {
        binding.btnCafe.setOnClickListener(this);
        binding.btnDrink.setOnClickListener(this);
        binding.btnStore.setOnClickListener(this);
        binding.imgQrCode.setOnClickListener(this);
    }
    private void init() {
        productPresenter = new ProductPresenter(getContext());
        productsCafe = productPresenter.getProductsCafe();
        productsDrink = productPresenter.getProductsDrink();
    }
    private void setFirstBackgroundButton() {
        setBackgroundBtn(binding.btnCafe, R.color.blue);
        setBackgroundBtn(binding.btnDrink, R.color.brown);
        setBackgroundBtn(binding.btnStore, R.color.blue);
    }
    private void setAdapter() {
        adapter = new ProductAdapter(productsCafe, this);
        binding.rvProduct.setAdapter(adapter);
    }
    @Override
    public void onClick(Object position) {
        productPresenter.handleCount(Constance.recyclerviewItem, (int) position, productsCafe, productsDrink, isCafe, adapter);
    }
    @Override
    public void reductionBtn(int position) {
        productPresenter.handleCount(Constance.recyclerviewItem, (int) position, productsCafe, productsDrink, isCafe, adapter);
    }
    @Override
    public void navigateToScanCodeActivity() {
        Intent intent = new Intent(getActivity(), ScanCodeActivity.class);
        startActivity(intent);
    }
    @Override
    public void setBackgroundBtn(Button button, int color) {
        button.setBackgroundColor(getContext().getResources().getColor(color));
    }
    @Override
    public void navigateToTotalMoneyFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("table", null);
        Navigation.findNavController(getView()).navigate(R.id.totalMoneyFragment, bundle);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cafe:
                setBackgroundBtn(binding.btnCafe, R.color.blue);
                setBackgroundBtn(binding.btnDrink, R.color.brown);
                adapter.updateProduct(productsCafe);
                isCafe = true;
                break;
            case R.id.btn_drink:
                setBackgroundBtn(binding.btnCafe, R.color.brown);
                setBackgroundBtn(binding.btnDrink, R.color.blue);
                adapter.updateProduct(productsDrink);
                isCafe = false;
                break;
            case R.id.btn_store:
                if(table != null){
                    Constance.TYPE_PAY = "SHELL";
                    Customer customer = (Customer) getArguments().getSerializable("customer");
                    productPresenter.addCustomer(customer);
                    productPresenter.addInvoice(productsCafe, productsDrink, "SHELL", table);
                    if(table.getCountCurrentPeople() == 0){
                        table.setCountCurrentPeople(customer.getCount());
                    }else {
                        table.setCountCurrentPeople(table.getCountCurrentPeople() + customer.getCount());
                    }
                    productPresenter.updateTable(table);
                    Navigation.findNavController(getView()).popBackStack();
                }else {
                    Constance.TYPE_PAY = "";
                    productPresenter.addInvoice(productsCafe, productsDrink, "", table);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("table", new Table());
                    Navigation.findNavController(getView()).navigate(R.id.totalMoneyFragment, bundle);
                }
                break;
            case R.id.img_qr_code:
                navigateToScanCodeActivity();
                break;
        }
    }
}
