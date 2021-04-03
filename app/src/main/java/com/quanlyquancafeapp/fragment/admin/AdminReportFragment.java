package com.quanlyquancafeapp.fragment.admin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.anychart.AnyChart;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentAdminReportBinding;
import com.quanlyquancafeapp.model.PieChartView;
import com.quanlyquancafeapp.presenter.admin.AdminReportPresenter;
import com.quanlyquancafeapp.utils.PriceUtil;
import com.quanlyquancafeapp.view.admin.IAdminReportView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdminReportFragment extends Fragment implements IAdminReportView {
    private FragmentAdminReportBinding binding;
    private AdminReportPresenter presenter;
    private ArrayList<PieChartView> pies;
    private Pie pie;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_report,container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new AdminReportPresenter(getContext());

        setBtnChooseDate();

        pie = AnyChart.pie();
        pies = presenter.getDetailInvoicesRevenueDetailPie(binding.btnChooseDate.getText().toString());
        List<DataEntry> dataEntries = new ArrayList<>();
        for(int i = 0; i < pies.size(); i++){
            dataEntries.add(new ValueDataEntry(pies.get(i).getDrinks(), pies.get(i).getCount()));
        }
        pie.data(dataEntries);
        binding.anyChartView.setChart(pie);

        binding.btnChooseDate.setOnClickListener(v->{
            showDatePickerDialog();
        });
        binding.rlRevenue.setOnClickListener(v->{
            navigateToAdminReport();
        });

        setTotalRevenueTextView(binding.btnChooseDate.getText().toString());
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
    @Override
    public void navigateToAdminReport() {
        Bundle bundle = new Bundle();
        bundle.putString("date", binding.btnChooseDate.getText().toString());
        Navigation.findNavController(getView()).navigate(R.id.revenueDetailFragment, bundle);
    }
    @Override
    public void showDatePickerDialog() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view1, year, monthOfYear, dayOfMonth)
                        ->{
                    binding.btnChooseDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                    setTotalRevenueTextView(binding.btnChooseDate.getText().toString());

                    pies = presenter.getDetailInvoicesRevenueDetailPie(binding.btnChooseDate.getText().toString());
                    List<DataEntry> dataEntries = new ArrayList<>();
                    for(int i = 0; i < pies.size(); i++){
                        dataEntries.add(new ValueDataEntry(pies.get(i).getDrinks(), pies.get(i).getCount()));
                    }
                    pie.data(dataEntries);
                    hideAndShowAnyChartView(dataEntries);
                }
                , mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    @Override
    public void setBtnChooseDate() {
        SimpleDateFormat getDate = new SimpleDateFormat("d/M/yyyy");
        Date date = new Date();
        binding.btnChooseDate.setText(getDate.format(date));
    }

    @Override
    public void hideAndShowAnyChartView(List<DataEntry> dataEntries) {
        if(dataEntries.size() == 0){
            binding.anyChartView.setVisibility(View.GONE);
        }else {
            binding.anyChartView.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void setTotalRevenueTextView(String date) {
        String setupMoney = PriceUtil.setupPrice(
                String.valueOf(presenter.getDetailInvoicesRevenueDetailTotalMoney(date)));
        binding.txtTotalRevenue.setText(setupMoney);
    }
}
