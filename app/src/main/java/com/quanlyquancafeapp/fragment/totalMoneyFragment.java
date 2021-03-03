package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.TotalMoneyAdapter;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.model.Table;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.PriceUtil;

import java.util.ArrayList;

public class totalMoneyFragment extends Fragment {
    private Button btnTotalMoney;
    private TotalMoneyAdapter adapter;
    private RecyclerView rvTotalMoney;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_total_money, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        Table table = (Table) getArguments().getSerializable("table");

        //demo
        float totalMoney = 0;
        ArrayList<Product> products = DataFake.productFake();
        for(int i = 0; i < DataFake.orders.size(); i++){
            for (int j = 0; j < products.size(); j++){
                float intoMoney = 0;
                if(DataFake.orders.get(i).getIdProduct() == products.get(j).getId() &&
                        DataFake.orders.get(i).getIdTable() == table.getId()){
                    if(products.get(j).getSale().equals("")){//check sale
                        float sum = products.get(j).getPrice() * DataFake.orders.get(i).getCount();
                        intoMoney += sum;
                        totalMoney += sum;
                    }else {
                        String saleStr = "";
                        for(int s = 0; s < products.get(j).getSale().length(); s++){
                            saleStr+=s;
                            if(products.get(j).getSale().charAt(s) == '%'){
                                break;
                            }
                        }
                        int sale = Integer.parseInt(saleStr);
                        float sum = (products.get(j).getPrice() * DataFake.orders.get(i).getCount() * (100-sale)/(float)100);
                        intoMoney += sum;
                        totalMoney += sum;
                        Log.d("KMFG","OKSALE==="+sum);
                    }
                    DataFake.orders.get(i).setTotalMoney(intoMoney);
                }
            }
        }
        Log.d("KMFG","OKSALE=="+totalMoney);
        String setupMoney = PriceUtil.setupPrice(String.valueOf(totalMoney));
        btnTotalMoney.setText(setupMoney);

        setAdapter();
    }
    private void setAdapter(){
        adapter = new TotalMoneyAdapter(DataFake.orders);
        rvTotalMoney.addItemDecoration(new DividerItemDecoration(rvTotalMoney.getContext(), DividerItemDecoration.VERTICAL));
        rvTotalMoney.setAdapter(adapter);
    }
    private void initView(){
        btnTotalMoney = getView().findViewById(R.id.btn_total_money);
        rvTotalMoney = getView().findViewById(R.id.rv_total_money);
    }
}
