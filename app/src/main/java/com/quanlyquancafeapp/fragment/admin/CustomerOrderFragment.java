package com.quanlyquancafeapp.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.CustomerOrderAdapter;
import com.quanlyquancafeapp.databinding.FragmentCustomerOrderBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import java.util.ArrayList;

public class CustomerOrderFragment extends Fragment implements CustomerOrderAdapter.IRecyclerViewItemOnClick {
    private FragmentCustomerOrderBinding binding;
    private CustomerOrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_order, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DatabaseHelper db = new DatabaseHelper(getContext());
        ArrayList<InvoiceDetail> invoiceDetails = db.getDetailInvoicesCustomer();
        adapter = new CustomerOrderAdapter(invoiceDetails, this);
        binding.rvCustomerOrder.setAdapter(adapter);
    }
    @Override
    public void onClick(Long idCustomer) {
        CustomerOrderBottomSheetFragment customerOrderBottomSheetFragment = new CustomerOrderBottomSheetFragment(idCustomer);
        customerOrderBottomSheetFragment.show(getChildFragmentManager(),customerOrderBottomSheetFragment.getTag());
    }
}
