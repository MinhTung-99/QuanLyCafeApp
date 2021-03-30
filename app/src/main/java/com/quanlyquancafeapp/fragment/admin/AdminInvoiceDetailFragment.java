package com.quanlyquancafeapp.fragment.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminProductInvoiceAdapter;
import com.quanlyquancafeapp.databinding.FragmentAdminInvoiceDetailBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.presenter.admin.AdminInvoiceDetailPresenter;

import java.util.ArrayList;

public class AdminInvoiceDetailFragment extends Fragment {
    private FragmentAdminInvoiceDetailBinding binding;
    private AdminProductInvoiceAdapter adapter;
    private AdminInvoiceDetailPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_invoice_detail,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AdminInvoiceDetailPresenter(getContext());
        Invoice invoice = (Invoice) getArguments().getSerializable("invoice");
        adapter = new AdminProductInvoiceAdapter(presenter.getDetailInvoicesById(invoice.getId()));
        binding.rvProducts.setAdapter(adapter);
    }
}
