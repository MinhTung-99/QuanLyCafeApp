package com.quanlyquancafeapp.fragment.admin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminTableAdapter;
import com.quanlyquancafeapp.databinding.DialogAddTableBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteTableBinding;
import com.quanlyquancafeapp.databinding.DialogUpdateTableBinding;
import com.quanlyquancafeapp.databinding.FragmentAdminTableBinding;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.admin.AdminTablePresenter;
import com.quanlyquancafeapp.utils.ToastUtils;
import com.quanlyquancafeapp.view.admin.IAdminTableView;

import java.util.ArrayList;

public class AdminTableFragment extends Fragment implements IAdminTableView, AdminTableAdapter.RecyclerViewItemOnClick {
    private FragmentAdminTableBinding fragmentAdminTableBinding;
    private AdminTablePresenter adminTablePresenter;
    private ArrayList<Table> tables;
    private AdminTableAdapter adapter;
    private DialogAddTableBinding dialogAddTableBinding;
    private DialogDeleteTableBinding dialogDeleteTableBinding;
    private DialogUpdateTableBinding dialogUpdateTableBinding;
    private AlertDialog alertDialogAdd;
    private AlertDialog alertDialogDelete;
    private AlertDialog alertDialogUpdate;
    private boolean isNameTable, isCountTable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddTableBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_table, container,false);
        dialogDeleteTableBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_table, container, false);
        dialogUpdateTableBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_update_table, container, false);
        fragmentAdminTableBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_table, container, false);
        return fragmentAdminTableBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        initDialogAdd();
        initDialogDelete();
        initDialogUpdate();
        setAdapterTable();

        fragmentAdminTableBinding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
        });

        dialogAddTableBinding.btnCancel.setOnClickListener(v->{
            alertDialogAdd.dismiss();
            dialogAddTableBinding.edtTableName.setText("");
            dialogAddTableBinding.edtTableCountPeople.setText("");
            dialogAddTableBinding.btnYes.setEnabled(false);
        });
        dialogAddTableBinding.btnYes.setOnClickListener(v->{
            if(adminTablePresenter.isSameNameTable(dialogAddTableBinding.edtTableName.getText().toString())){
                ToastUtils.showToast(getActivity(), "Tên bàn đã tồn tại");
            }else {
                if(Integer.parseInt(dialogAddTableBinding.edtTableCountPeople.getText().toString()) > 0){
                    Table table = new Table();
                    table.setName(dialogAddTableBinding.edtTableName.getText().toString());
                    table.setCountPeople(Integer.parseInt(dialogAddTableBinding.edtTableCountPeople.getText().toString()));
                    adminTablePresenter.addTable(table);
                    updateTableAdapter();
                    alertDialogAdd.dismiss();
                    dialogAddTableBinding.edtTableName.setText("");
                    dialogAddTableBinding.edtTableCountPeople.setText("");
                }else {
                    ToastUtils.showToast(getActivity(), "Số người phải lớn hơn không");
                }
            }
        });
        listenEdt();
    }
    private void listenEdt(){
        dialogAddTableBinding.edtTableName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isNameTable = true;
                }else {
                    isNameTable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameTable && isCountTable){
                    dialogAddTableBinding.btnYes.setEnabled(true);
                }else {
                    dialogAddTableBinding.btnYes.setEnabled(false);
                }
            }
        });
        dialogAddTableBinding.edtTableCountPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isCountTable = true;
                }else {
                    isCountTable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameTable && isCountTable){
                    dialogAddTableBinding.btnYes.setEnabled(true);
                }else {
                    dialogAddTableBinding.btnYes.setEnabled(false);
                }
            }
        });

        dialogUpdateTableBinding.edtTableName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isNameTable = true;
                }else {
                    isNameTable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameTable && isCountTable){
                    dialogUpdateTableBinding.btnYes.setEnabled(true);
                }else {
                    dialogUpdateTableBinding.btnYes.setEnabled(false);
                }
            }
        });
        dialogUpdateTableBinding.edtTableCountPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isCountTable = true;
                }else {
                    isCountTable = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isNameTable && isCountTable){
                    dialogUpdateTableBinding.btnYes.setEnabled(true);
                }else {
                    dialogUpdateTableBinding.btnYes.setEnabled(false);
                }
            }
        });
    }
    private void updateTableAdapter(){
        tables = adminTablePresenter.getTables();
        adapter.updateTableAdmin(tables);
        fragmentAdminTableBinding.rvTable.setAdapter(adapter);
    }
    private void init() {
        adminTablePresenter = new AdminTablePresenter(this, getContext());
        tables = adminTablePresenter.getTables();
    }
    private void setAdapterTable(){
        adapter = new AdminTableAdapter(tables, this);
        fragmentAdminTableBinding.rvTable.setAdapter(adapter);
    }

    private String nameTable;
    @Override
    public void onItemLongClick(Table table) {
        nameTable = table.getName();

        dialogUpdateTableBinding.edtTableName.setText(table.getName());
        dialogUpdateTableBinding.edtTableCountPeople.setText(String.valueOf(table.getCountPeople()));

        alertDialogUpdate.show();

        dialogUpdateTableBinding.btnCancel.setOnClickListener(v->{
            alertDialogUpdate.dismiss();
        });

        dialogUpdateTableBinding.btnYes.setOnClickListener(v->{
            if(adminTablePresenter.isSameNameTable(dialogUpdateTableBinding.edtTableName.getText().toString())
                && !dialogUpdateTableBinding.edtTableName.getText().toString().toLowerCase().equals(nameTable.toLowerCase())){

                ToastUtils.showToast(getActivity(), "Tên bàn đã tồn tại");

            }else {
                if(Integer.parseInt(dialogUpdateTableBinding.edtTableCountPeople.getText().toString()) > 0){
                    table.setName(dialogUpdateTableBinding.edtTableName.getText().toString());
                    table.setCountPeople(Integer.parseInt(dialogUpdateTableBinding.edtTableCountPeople.getText().toString()));
                    adminTablePresenter.updateTable(table);
                    updateTableAdapter();
                    dialogUpdateTableBinding.edtTableName.setText("");
                    alertDialogUpdate.dismiss();
                }else {
                    ToastUtils.showToast(getActivity(), "Số người phải lớn hơn không");
                }
            }
        });
    }

    @Override
    public void btnDeleteOnClick(Table table) {
        alertDialogDelete.show();

        dialogDeleteTableBinding.btnCancel.setOnClickListener(v->{
            alertDialogDelete.dismiss();
        });

        dialogDeleteTableBinding.btnYes.setOnClickListener(v->{
            adminTablePresenter.deleteTable(table.getId());
            updateTableAdapter();
            adapter.closeAllSwipe();
            alertDialogDelete.dismiss();
        });
    }
    @Override
    public void initDialogAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogAddTableBinding.getRoot());
        alertDialogAdd = dialogBuilder.create();
        alertDialogAdd.setCancelable(false);
    }
    @Override
    public void initDialogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteTableBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
        alertDialogDelete.setCancelable(false);
    }
    @Override
    public void initDialogUpdate() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogUpdateTableBinding.getRoot());
        alertDialogUpdate = dialogBuilder.create();
        alertDialogUpdate.setCancelable(false);
    }
}
