package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentPayBinding;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.presenter.PayPresenter;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.PriceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PayFragment extends Fragment {
    private FragmentPayBinding binding;
    private PayPresenter payPresenter;
    private Invoice invoice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        invoice = new Invoice();
        payPresenter = new PayPresenter(getContext());
        float totalMoney = getArguments().getFloat("totalMoney");
        long[] idInvoiceDetail = getArguments().getLongArray("idInvoiceDetail");
        String setupMoney = PriceUtil.setupPrice(String.valueOf(totalMoney));
        binding.txtTotalMoney.setText(setupMoney);

        binding.edtMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    binding.txtRefunds.setText(String.valueOf(0));
                }else {
                    float money = Float.parseFloat(s.toString());
                    float moneyRefunds = money - totalMoney;
                    String setupMoney = PriceUtil.setupPrice(String.valueOf(moneyRefunds));
                    binding.txtRefunds.setText(setupMoney);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.btnPay.setOnClickListener(v->{
            for(int i = 0; i < idInvoiceDetail.length; i++){
                invoice.setId(idInvoiceDetail[i]);
                Log.d("KMFG1", idInvoiceDetail[i]+ " ==");
                invoice.setTotalMoney(totalMoney);
                //invoice.setInToMoney();
                SimpleDateFormat getDate = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat getTime = new SimpleDateFormat("HH:mm:ss");
                Date date = new Date();
                invoice.setDateBuy(getDate.format(date));
                invoice.setTime(getTime.format(date));
                invoice.setIsPay(1);
                invoice.setIdTable(0L);
                payPresenter.updateInvoice(invoice);
            }
//            for(int i = 0; i < 2; i++){
//                Navigation.findNavController(v).popBackStack();
//            }
        });
    }
}
