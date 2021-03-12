package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;

import com.quanlyquancafeapp.databinding.ActivityHomeBinding;
import com.quanlyquancafeapp.presenter.HomePresenter;
import com.quanlyquancafeapp.view.IHomeView;

public class HomeActivity extends AppCompatActivity implements IHomeView {
    private ActivityHomeBinding binding;
    private HomePresenter homePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homePresenter = new HomePresenter(this);
        binding.llSell.setOnClickListener(v->homePresenter.sell());
        binding.llPayment.setOnClickListener(v->homePresenter.payment());
    }
    @Override
    public void navigateToTableFragment() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("TypeAdmin","SHELL");
        startActivity(intent);
    }

    @Override
    public void navigateToProductFragment() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("TypeAdmin","PAY");
        startActivity(intent);
    }
}