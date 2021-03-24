package com.quanlyquancafeapp.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminInvoiceAdapter;
import com.quanlyquancafeapp.databinding.FragmentAdminInvoiceBinding;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.presenter.admin.AdminInvoicePresenter;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;
import java.util.ArrayList;

public class AdminInvoiceFragment extends Fragment implements IRecyclerViewOnItemClick {
    private FragmentAdminInvoiceBinding binding;
    private AdminInvoiceAdapter adapter;
    private ArrayList<Invoice> invoices;
    private AdminInvoicePresenter adminInvoicePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_invoice,container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adminInvoicePresenter = new AdminInvoicePresenter(getContext());
        invoices = adminInvoicePresenter.getInvoiceSort();
        adapter = new AdminInvoiceAdapter(invoices, this);
        binding.rvInvoices.setAdapter(adapter);
    }
    @Override
    public void onClick(Object model) {
        Invoice invoice = (Invoice) model;
        Bundle bundle = new Bundle();
        bundle.putSerializable("invoice", invoice);
        Navigation.findNavController(getView()).navigate(R.id.invoiceDetailFragment, bundle);
    }
    @Override
    public void reductionBtn(int position) { }
}
