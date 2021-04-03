package com.quanlyquancafeapp.view.admin;

import com.anychart.chart.common.dataentry.DataEntry;

import java.util.List;

public interface IAdminReportView {
    void navigateToAdminReport();
    void showDatePickerDialog();
    void setBtnChooseDate();
    void hideAndShowAnyChartView(List<DataEntry> dataEntries);
}
