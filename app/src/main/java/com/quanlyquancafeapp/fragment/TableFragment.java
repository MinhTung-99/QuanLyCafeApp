package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.TableAdapter;
import com.quanlyquancafeapp.databinding.DialogAddTableBinding;
import com.quanlyquancafeapp.databinding.FragmentTableBinding;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class TableFragment extends Fragment implements IRecyclerViewOnItemClick {
    private TableAdapter adapter;
    private ArrayList<Table> tables;
    private FragmentTableBinding tableBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tableBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_table, container, false);
        return tableBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAdapter();
    }
    private void setAdapter() {
        tables = new ArrayList<>();
//        tables.add(new Table(1L,"001","ban 1 tang 1"));
//        tables.add(new Table(2L,"002","ban 2 tang 1"));
        tableBinding.rvTable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new TableAdapter(tables, this);
        tableBinding.rvTable.setAdapter(adapter);
    }
    @Override
    public void onClick(Object model) {
        Table table = (Table) model;
        if(DataFake.order.getIdTable() == table.getId()){
            Bundle bundle = new Bundle();
            bundle.putSerializable("table", (Table) model);
            Navigation.findNavController(getView()).navigate(R.id.totalMoneyFragment, bundle);
        }else{
            Bundle bundle = new Bundle();
            bundle.putSerializable("table", (Table) model);
            Navigation.findNavController(getView()).navigate(R.id.productFragment, bundle);
        }
    }
    @Override
    public void reductionBtn(int position) { }
}
