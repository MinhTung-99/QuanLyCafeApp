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
        ArrayList<InvoiceDetail> invoiceDetailArrayList = new ArrayList<>();
        for(int i = 0; i < invoiceDetails.size()-1; i++){
            if(invoiceDetails.get(i).getCustomer().getId() != invoiceDetails.get(i+1).getCustomer().getId()){
                invoiceDetailArrayList.add(invoiceDetails.get(i));
            }
        }
        if(invoiceDetails.get(invoiceDetails.size()-1).getCustomer().getId() != invoiceDetails.get(invoiceDetails.size()-2).getCustomer().getId()){
            invoiceDetailArrayList.add(invoiceDetails.get(invoiceDetails.size()-1));
        }
        adapter = new CustomerOrderAdapter(invoiceDetailArrayList,this);
        binding.rvCustomerOrder.setAdapter(adapter);
    }
    @Override
    public void onClick(Long idCustomer) {
        CustomerOrderBottomSheetFragment customerOrderBottomSheetFragment = new CustomerOrderBottomSheetFragment(idCustomer);
        customerOrderBottomSheetFragment.show(getChildFragmentManager(),customerOrderBottomSheetFragment.getTag());
    }

    @Override
    public void btnTotalMoney(Long idTable) {
        Bundle bundle = new Bundle();
        Table table = new Table();
        table.setId(idTable);
        bundle.putSerializable("table",table);
        Navigation.findNavController(getView()).navigate(R.id.totalMoneyFragment, bundle);
    }
}
