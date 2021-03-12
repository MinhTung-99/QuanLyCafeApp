package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.InvoiceAdapter;
import com.quanlyquancafeapp.databinding.FragmentInvoiceBinding;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class InvoiceFragment extends Fragment implements IRecyclerViewOnItemClick {
    private FragmentInvoiceBinding binding;
    private InvoiceAdapter adapter;
    private ArrayList<Invoice> invoices;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_invoice,container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        invoices = DataFake.invoiceFake();
        adapter = new InvoiceAdapter(invoices, this);
        binding.rvInvoices.setAdapter(adapter);
    }
    @Override
    public void onClick(Object model) {
        Invoice invoice = (Invoice) model;
        Log.d("KMFG", invoice.getDate());
        Bundle bundle = new Bundle();
        bundle.putSerializable("invoice", (Invoice) model);
        Navigation.findNavController(getView()).navigate(R.id.invoiceDetailFragment, bundle);
    }
    @Override
    public void reductionBtn(int position) { }
}
