package com.quanlyquancafeapp.fragment.admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.CustomerOrderBottomSheetAdapter;
import com.quanlyquancafeapp.databinding.FragmentCustomerOrderBottomSheetBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.presenter.admin.CustomerOrderBottomSheetPresenter;

import java.util.ArrayList;

public class CustomerOrderBottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentCustomerOrderBottomSheetBinding bottomSheetBinding;
    private CustomerOrderBottomSheetAdapter adapter;
    private Long idCustomer;
    private CustomerOrderBottomSheetPresenter presenter;

    public CustomerOrderBottomSheetFragment(Long idCustomer) {
        this.idCustomer = idCustomer;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_order_bottom_sheet, container, false);
        return bottomSheetBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new CustomerOrderBottomSheetPresenter(getContext());
        adapter = new CustomerOrderBottomSheetAdapter(presenter.getDetailInvoicesCustomer(idCustomer));
        bottomSheetBinding.rvCustomerOrderBottomSheet.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(2300);
            bottomSheetBehavior.setHideable(false);
        });
    }
}
