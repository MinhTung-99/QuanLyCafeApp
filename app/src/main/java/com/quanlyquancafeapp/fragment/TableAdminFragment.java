package com.quanlyquancafeapp.fragment;

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
import com.quanlyquancafeapp.databinding.FragmentTableAdminBinding;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.DataFake;

import java.util.ArrayList;

public class TableAdminFragment extends Fragment {
    private FragmentTableAdminBinding binding;
    private ArrayList<Table> tables;
    private TableAdminAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_table_admin, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tables = DataFake.tableFake();
        adapter = new TableAdminAdapter(tables);
        binding.rvTable.setAdapter(adapter);
    }
}
