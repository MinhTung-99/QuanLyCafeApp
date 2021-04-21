package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.quanlyquancafeapp.adapter.TimekeepingAdapter;
import com.quanlyquancafeapp.databinding.ActivityTimekeepingBinding;
import com.quanlyquancafeapp.presenter.TimekeepingPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimekeepingActivity extends AppCompatActivity {
    private ActivityTimekeepingBinding binding;
    private String userName;
    private boolean isAdmin;
    private TimekeepingPresenter presenter;
    private TimekeepingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timekeeping);
        presenter = new TimekeepingPresenter(this);

        SimpleDateFormat getDate = new SimpleDateFormat("d/M/yyyy");
        Date date = new Date();
        binding.txtDate.setText(getDate.format(date));

        userName = getIntent().getExtras().getString("user_name");
        isAdmin = getIntent().getExtras().getBoolean("isAdmin");

        adapter = new TimekeepingAdapter(presenter.getUserTime(userName), binding.txtDate.getText().toString(),this);
        binding.rvUsrWorking.setAdapter(adapter);

        if(isAdmin){
            binding.toolbar.setOnClickListener(v->{
                showDatePickerDialog();
            });
        }
    }

    public void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(TimekeepingActivity.this,
                (view1, year, monthOfYear, dayOfMonth) ->{
                    binding.txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    adapter = new TimekeepingAdapter(presenter.getUserTime(userName), binding.txtDate.getText().toString(),this);
                    binding.rvUsrWorking.setAdapter(adapter);
                }
                , mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}