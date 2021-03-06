package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentHomeBinding;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.HomePresenter;
import com.quanlyquancafeapp.view.IHomeView;

public class HomeFragment extends Fragment implements IHomeView {
    private FragmentHomeBinding binding;
    private HomePresenter homePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home,container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        binding.llSell.setOnClickListener(v->homePresenter.sell());
        binding.llPayment.setOnClickListener(v->homePresenter.payment());
    }
    private void initView() {
        homePresenter = new HomePresenter(this);
    }
    @Override
    public void navigateToTableFragment() {
        Navigation.findNavController(getView()).navigate(R.id.tableFragment);
    }

    @Override
    public void navigateToProductFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("table", null);
        Navigation.findNavController(getView()).navigate(R.id.productFragment, bundle);
    }
}
