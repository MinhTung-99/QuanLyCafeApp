package com.quanlyquancafeapp.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.CustomerOrderAdapter;
import com.quanlyquancafeapp.databinding.FragmentCustomerOrderBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.admin.CustomerOrderPresenter;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.view.admin.ICustomerOrderView;

import java.util.ArrayList;

public class CustomerOrderFragment extends Fragment implements CustomerOrderAdapter.IRecyclerViewItemOnClick, ICustomerOrderView {
    private FragmentCustomerOrderBinding binding;
    private CustomerOrderAdapter adapter;
    private CustomerOrderPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_order, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CustomerOrderPresenter(getContext());
        adapter = new CustomerOrderAdapter(presenter.getDetailInvoicesCustomer(),this);
        binding.rvCustomerOrder.setAdapter(adapter);
    }
    @Override
    public void onClick(Long idCustomer) {
        navigateToCustomerOrderBottomSheetFragment(idCustomer);
    }
    @Override
    public void btnTotalMoney(Long idTable) {
        Constance.TYPE_PAY = "SHELL";
        Bundle bundle = new Bundle();
        Table table = new Table();
        table.setId(idTable);
        bundle.putSerializable("table",table);
        Navigation.findNavController(getView()).navigate(R.id.totalMoneyFragment, bundle);
    }
    @Override
    public void navigateToCustomerOrderBottomSheetFragment(Long idCustomer) {
        CustomerOrderBottomSheetFragment customerOrderBottomSheetFragment = new CustomerOrderBottomSheetFragment(idCustomer);
        customerOrderBottomSheetFragment.show(getChildFragmentManager(),customerOrderBottomSheetFragment.getTag());
    }
}
