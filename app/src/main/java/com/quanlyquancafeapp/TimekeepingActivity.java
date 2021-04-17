package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.quanlyquancafeapp.adapter.TimekeepingAdapter;
import com.quanlyquancafeapp.databinding.ActivityTimekeepingBinding;
import com.quanlyquancafeapp.presenter.TimekeepingPresenter;

public class TimekeepingActivity extends AppCompatActivity {
    private ActivityTimekeepingBinding binding;
    private String userName;
    private TimekeepingPresenter presenter;
    private TimekeepingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timekeeping);
        presenter = new TimekeepingPresenter(this);

        userName = getIntent().getExtras().getString("user_name");
        adapter = new TimekeepingAdapter(presenter.getUserTime(userName), this);
        binding.rvUsrWorking.setAdapter(adapter);
        //Toast.makeText(this, getIntent().getExtras().getString("user_name"),Toast.LENGTH_SHORT).show();
    }
}