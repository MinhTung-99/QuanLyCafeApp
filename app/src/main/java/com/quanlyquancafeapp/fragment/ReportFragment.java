package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentReportBinding;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment { ;
    private FragmentReportBinding binding;
    String[] drinks = {"Cafe sữa","Cafe đá","7 up", "Coca"};
    int[] earning = {500,800,2000,600};
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
    }
}
