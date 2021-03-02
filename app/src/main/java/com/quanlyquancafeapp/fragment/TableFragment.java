package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.TableAdapter;
import com.quanlyquancafeapp.model.Order;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class TableFragment extends Fragment implements IRecyclerViewOnItemClick {
    private TableAdapter adapter;
    private ArrayList<Table> tables;
    private RecyclerView rvTable;
    //demo
    public static Order order = new Order();
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
        Log.d("KMFG","oooo");
    }
    private void setAdapter() {
        tables.add(new Table(1L,"001","ban 1 tang 1"));
        tables.add(new Table(2L,"002","ban 2 tang 1"));
        rvTable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new TableAdapter(tables, this);
        adapter.updateTable(tables);
        rvTable.setAdapter(adapter);
    }
    private void initView() {
        tables = new ArrayList<>();
        rvTable = getView().findViewById(R.id.rv_table);
    }
    @Override
    public void onClick(Object model) {
        //
        Bundle bundle = new Bundle();
        bundle.putSerializable("table", (Table) model);
        Navigation.findNavController(getView()).navigate(R.id.productFragment, bundle);
    }
    @Override
    public void reductionBtn(int position) { }
}
