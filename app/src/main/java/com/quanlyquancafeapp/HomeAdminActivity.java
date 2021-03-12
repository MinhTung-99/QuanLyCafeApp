package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.quanlyquancafeapp.adapter.AdminAdapter;
import com.quanlyquancafeapp.databinding.ActivityHomeAdminBinding;
import com.quanlyquancafeapp.fragment.ReportFragment;
import com.quanlyquancafeapp.model.Admin;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class HomeAdminActivity extends AppCompatActivity implements IRecyclerViewOnItemClick {
    private ActivityHomeAdminBinding binding;
    private AdminAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_admin);

        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin(R.drawable.rounded_green,R.drawable.ic_speaker,"Báo cáo"));
        admins.add(new Admin(R.drawable.rounded_teal_200,R.drawable.ic_chair,"Bàn"));
        admins.add(new Admin(R.drawable.rounded_blue,R.drawable.ic_user,"Nhân viên"));
        admins.add(new Admin(R.drawable.rounded_orange,R.drawable.ic_store,"Kho"));
        adapter = new AdminAdapter(admins, this);
        binding.rvAdmin.setLayoutManager(new GridLayoutManager(this, 2));
        binding.rvAdmin.setAdapter(adapter);
    }

    @Override
    public void onClick(Object model) {
        int position = (int) model;
        switch (position){
            case 0:
                startActivity("REPORT");
                break;
            case 1:
                startActivity("TABLE");
                break;
            case 2:
                startActivity("USER");
                break;
            case 3:
                startActivity("STORE");
                break;
        }
    }
    private void startActivity(String value){
        Intent intent = new Intent(HomeAdminActivity.this, MainActivity.class);
        intent.putExtra("TypeAdmin", value);
        startActivity(intent);
    }
    @Override
    public void reductionBtn(int position) { }
}