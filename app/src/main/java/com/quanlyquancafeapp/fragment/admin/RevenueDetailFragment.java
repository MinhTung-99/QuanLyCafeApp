package com.quanlyquancafeapp.fragment.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.RevenueDetailAdapter;
import com.quanlyquancafeapp.databinding.FragmentDetailRevenueBinding;
import com.quanlyquancafeapp.presenter.admin.RevenueDetailPresenter;

public class RevenueDetailFragment extends Fragment {

    private FragmentDetailRevenueBinding binding;
    private RevenueDetailAdapter adapter;
    private RevenueDetailPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_revenue, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RevenueDetailPresenter(getContext());
        adapter = new RevenueDetailAdapter(presenter.getInvoiceDetailRevenueDetail(getArguments().getString("date")));
        binding.rvRevenueDetail.setAdapter(adapter);
    }
}
