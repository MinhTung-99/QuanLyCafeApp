package com.quanlyquancafeapp.presenter;

import com.quanlyquancafeapp.view.ITableView;

public class TablePresenter {
    private ITableView tableView;
    public TablePresenter(ITableView tableView) {
        this.tableView = tableView;
    }
}
