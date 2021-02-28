package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.presenter.HomePresenter;
import com.quanlyquancafeapp.view.IHomeView;

public class HomeFragment extends Fragment implements IHomeView {
    private LinearLayout llShell;
    private HomePresenter homePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        llShell.setOnClickListener(v->homePresenter.sell());
    }
    private void initView() {
        homePresenter = new HomePresenter(this);
        llShell = getView().findViewById(R.id.ll_sell);
    }
    @Override
    public void navigateToTableFragment() {
        Navigation.findNavController(getView()).navigate(R.id.tableFragment);
    }
}
