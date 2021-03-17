package com.quanlyquancafeapp.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.TableAdminAdapter;
import com.quanlyquancafeapp.databinding.DialogAddTableBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteTableBinding;
import com.quanlyquancafeapp.databinding.FragmentTableAdminBinding;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.TableAdminPresenter;
import com.quanlyquancafeapp.view.ITableAdminView;

import java.util.ArrayList;

public class TableAdminFragment extends Fragment implements ITableAdminView, TableAdminAdapter.RecyclerViewItemOnClick {
    private FragmentTableAdminBinding binding;
    private TableAdminPresenter tableAdminPresenter;
    private ArrayList<Table> tables;
    private TableAdminAdapter adapter;
    private DialogAddTableBinding dialogAddTableBinding;
    private DialogDeleteTableBinding dialogDeleteTableBinding;
    private AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddTableBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_table, container,false);
        dialogDeleteTableBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_table, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_admin, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        initDialog(dialogAddTableBinding.getRoot());

        setAdapterTable();
        binding.imgAdd.setOnClickListener(v->{
            alertDialog.show();
        });
        dialogAddTableBinding.btnCancel.setOnClickListener(v->{
            alertDialog.dismiss();
        });
        dialogAddTableBinding.btnYes.setOnClickListener(v->{
            Table table = new Table();
            table.setName(dialogAddTableBinding.edtTableName.getText().toString());
            tableAdminPresenter.addTable(table);
            tables = tableAdminPresenter.getTables();
            adapter.updateTableAdmin(tables);
            binding.rvTable.setAdapter(adapter);
            alertDialog.dismiss();
        });
        dialogDeleteTableBinding.btnCancel.setOnClickListener(v->{
            alertDialog.dismiss();
        });
    }
    private void init() {
        tableAdminPresenter = new TableAdminPresenter(this, getContext());
        tables = tableAdminPresenter.getTables();
    }
    private void setAdapterTable(){
        adapter = new TableAdminAdapter(tables, this);
        binding.rvTable.setAdapter(adapter);
    }
    @Override
    public void btnDeleteOnClick(Table table) {
//        tableAdminPresenter.deleteTable(table.getId());
//        tables = tableAdminPresenter.getTables();
//        adapter.updateTableAdmin(tables);
//        binding.rvTable.setAdapter(adapter);
        ((ViewGroup)dialogDeleteTableBinding.getRoot()).removeView(dialogDeleteTableBinding.getRoot());
        initDialog(dialogDeleteTableBinding.getRoot());
        alertDialog.show();
    }

    @Override
    public void initDialog(View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(view);
        alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
    }
}
