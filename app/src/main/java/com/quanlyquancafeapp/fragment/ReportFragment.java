package com.quanlyquancafeapp.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.quanlyquancafeapp.HomeActivity;
import com.quanlyquancafeapp.HomeAdminActivity;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentReportBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportFragment extends Fragment { ;
    private FragmentReportBinding binding;
    String[] drinks = {"Cafe sữa","Cafe đá","7 up", "Coca"};
    int[] earning = {500,800,2000,600};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Pie pie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i = 0; i < drinks.length; i++){
            dataEntries.add(new ValueDataEntry(drinks[i], earning[i]));
        }
        pie.data(dataEntries);
        binding.anyChartView.setChart(pie);

        SimpleDateFormat getDate = new SimpleDateFormat("d/M/yyyy");
        Date date = new Date();
        binding.btnChooseDate.setText(getDate.format(date));
        binding.btnChooseDate.setOnClickListener(v->{
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                    (view1, year, monthOfYear, dayOfMonth)
                            -> binding.btnChooseDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year)
                    , mYear, mMonth, mDay);
            datePickerDialog.show();
        });
        binding.rlRevenue.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            bundle.putString("date", binding.btnChooseDate.getText().toString());
            Navigation.findNavController(v).navigate(R.id.revenueDetailFragment, bundle);
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_report, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_invoice:
                Navigation.findNavController(getView()).navigate(R.id.invoiceFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
