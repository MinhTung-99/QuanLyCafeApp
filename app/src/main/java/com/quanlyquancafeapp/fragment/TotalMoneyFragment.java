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
import androidx.recyclerview.widget.DividerItemDecoration;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.TotalMoneyAdapter;
import com.quanlyquancafeapp.databinding.FragmentTotalMoneyBinding;
import com.quanlyquancafeapp.model.InvoiceDetail;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.presenter.TotalMoneyPresenter;
import com.quanlyquancafeapp.utils.PriceUtil;
import com.quanlyquancafeapp.view.ITotalMoneyView;

import java.util.ArrayList;

public class TotalMoneyFragment extends Fragment implements ITotalMoneyView {
    private TotalMoneyPresenter totalMoneyPresenter;
    private TotalMoneyAdapter adapter;
    private ArrayList<InvoiceDetail> invoicesNotPay;
    private FragmentTotalMoneyBinding totalMoneyBinding;

    private Long idCustomer;
    private Table table;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        totalMoneyBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_total_money, container, false);
        return totalMoneyBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        table = (Table) getArguments().getSerializable("table");
        idCustomer = getArguments().getLong("idCustomer");
        float totalMoney;
        totalMoney = totalMoneyPresenter.handleTotalMoney(table, invoicesNotPay, idCustomer);

        String setupMoney = PriceUtil.setupPrice(String.valueOf(totalMoney));
        totalMoneyBinding.btnTotalMoney.setText(setupMoney);
        totalMoneyBinding.btnTotalMoney.setOnClickListener(v->{
            navigateToPayFragment(totalMoney);
        });
        setAdapter();
    }
    private void init(){
        totalMoneyPresenter = new TotalMoneyPresenter(getContext());
        invoicesNotPay = new ArrayList<>();
    }
    private void setAdapter(){
        adapter = new TotalMoneyAdapter(invoicesNotPay, getContext());
        totalMoneyBinding.rvTotalMoney.addItemDecoration(new DividerItemDecoration(totalMoneyBinding.rvTotalMoney.getContext(), DividerItemDecoration.VERTICAL));
        totalMoneyBinding.rvTotalMoney.setAdapter(adapter);
    }
    @Override
    public void navigateToPayFragment(float totalMoney) {
        Bundle bundle = new Bundle();
        bundle.putFloat("totalMoney", totalMoney);
        bundle.putLongArray("idInvoiceDetail", totalMoneyPresenter.getCurrentIdInvoiceDetail());
        bundle.putLong("idCustomer", idCustomer);
        if(table != null){
            bundle.putLong("idTable", table.getId());
        }
        Navigation.findNavController(getView()).navigate(R.id.payFragment, bundle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        invoicesNotPay.clear();
    }
}
