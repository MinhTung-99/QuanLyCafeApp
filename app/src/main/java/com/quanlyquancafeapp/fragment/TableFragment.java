package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.TableAdapter;
import com.quanlyquancafeapp.model.Table;

import java.util.ArrayList;

public class TableFragment extends Fragment implements TableAdapter.IRecyclerViewOnClick{
    private TableAdapter adapter;
    private ArrayList<Table> tables;
    private RecyclerView rvTable;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_table, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        setAdapter();
    }
    private void setAdapter() {
        tables.add(new Table(1L,"001","ban 1 tang 1"));
        tables.add(new Table(2L,"002","ban 2 tang 1"));
        rvTable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new TableAdapter(tables, this);
        rvTable.setAdapter(adapter);
    }
    private void initView() {
        tables = new ArrayList<>();
        rvTable = getView().findViewById(R.id.rv_table);
    }
    @Override
    public void onClick(Table table) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("table", table);
        Navigation.findNavController(getView()).navigate(R.id.productFragment, bundle);
    }
}
