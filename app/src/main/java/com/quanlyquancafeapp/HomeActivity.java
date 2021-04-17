package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.quanlyquancafeapp.databinding.ActivityHomeBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.presenter.HomePresenter;
import com.quanlyquancafeapp.view.IHomeView;

public class HomeActivity extends AppCompatActivity implements IHomeView {
    private ActivityHomeBinding binding;
    private HomePresenter homePresenter;

    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homePresenter = new HomePresenter(this);

        userName = getIntent().getExtras().getString("user_name");
        //Toast.makeText(this, getIntent().getExtras().getString("user_name"),Toast.LENGTH_SHORT).show();

        binding.btnShell.setOnClickListener(v->homePresenter.sell());

        binding.btnPay.setOnClickListener(v->homePresenter.payment());

        binding.btnTimekeeping.setOnClickListener(v->navigateToTimekeepingActivity());
    }
    @Override
    public void navigateToTableFragment() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("TypeAdmin","CUSTOMERS");
        startActivity(intent);
    }

    @Override
    public void navigateToProductFragment() {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.putExtra("TypeAdmin","PAY");
        startActivity(intent);
    }

    @Override
    public void navigateToTimekeepingActivity() {
        Intent intent = new Intent(HomeActivity.this, TimekeepingActivity.class);
        intent.putExtra("user_name", userName);
        startActivity(intent);
    }
}