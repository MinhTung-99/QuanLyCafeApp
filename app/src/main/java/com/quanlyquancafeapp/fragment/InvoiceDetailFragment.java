package com.quanlyquancafeapp.fragment;

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
import com.quanlyquancafeapp.adapter.TotalMoneyAdapter;
import com.quanlyquancafeapp.databinding.FragmentInvoiceDetailBinding;
import com.quanlyquancafeapp.model.InvoiceOld;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.utils.DataFake;

import java.util.ArrayList;

public class InvoiceDetailFragment extends Fragment {
    private FragmentInvoiceDetailBinding binding;
    private ArrayList<Order> orders;
    private TotalMoneyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invoice_detail,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        orders = new ArrayList<>();
        InvoiceOld invoice = (InvoiceOld) getArguments().getSerializable("invoice");
        Toast.makeText(getContext(), invoice.getDate(),Toast.LENGTH_SHORT).show();
        DataFake.orders = DataFake.orderFake();
//        ArrayList<Product> products = DataFake.productFake();
//        for(Product product: products){
//            if(invoice.getIdProduct() == product.getId()){
//                orders.add(invoice);
//            }
//        }
//        adapter = new TotalMoneyAdapter(DataFake.orders);
//        binding.rvInvoices.setAdapter(adapter);
    }
}
