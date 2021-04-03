package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.quanlyquancafeapp.model.Customer;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.TablePresenter;
import com.quanlyquancafeapp.presenter.admin.AdminTablePresenter;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class TableFragment extends Fragment implements TableAdapter.RecyclerViewItemOnClick {
    private TableAdapter adapter;
    private ArrayList<Table> tables;
    private FragmentTableBinding tableBinding;
    private TablePresenter tablePresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tableBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_table, container, false);
        return tableBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tablePresenter = new TablePresenter(getContext());
        setAdapter();
    }
    private void setAdapter() {
        tables = tablePresenter.getTables();
        tableBinding.rvTable.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new TableAdapter(tables, this, getContext());
        tableBinding.rvTable.setAdapter(adapter);
    }
    @Override
    public void onClick(int position) {
//        boolean isTable = false;
//        for (int i = 0; i < tablePresenter.getInvoicesDetail().size(); i++){
//            if(tablePresenter.getInvoicesDetail().get(i).getIdTable() != null){
//                if(tablePresenter.getInvoicesDetail().get(i).getIdTable() == tables.get(position).getId()){
//                    //Constance.TYPE_PAY = getArguments().getString("typePay");
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("table", tables.get(position));
//                    Navigation.findNavController(getView()).navigate(R.id.totalMoneyFragment, bundle);
//                    isTable = true;
//                }
//            }
//        }
//        if(!isTable){
            Bundle bundle = new Bundle();
            Customer customer = (Customer) getArguments().getSerializable("customer");
            bundle.putSerializable("table", tables.get(position));
            bundle.putSerializable("customer", customer);
//            bundle.putString("typePay", getArguments().getString("typePay"));
            Navigation.findNavController(getView()).navigate(R.id.productFragment, bundle);
      //  }
    }
}
