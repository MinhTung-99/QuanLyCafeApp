package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.quanlyquancafeapp.databinding.ActivityCustomerOrderBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;

public class CustomerOrderActivity extends AppCompatActivity {
    private ActivityCustomerOrderBinding binding;
    private boolean isName = false, isCount = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_order);

        DatabaseHelper db = new DatabaseHelper(this);
        db.getDetailInvoicesNotTableById(1L);

        binding.btnRegister.setBackground(getDrawable(R.drawable.rounded_white_state_enable_btn));
        binding.btnRegister.setEnabled(false);

        binding.btnRegister.setOnClickListener(v->{
            Customer customer = new Customer();
            customer.setName(binding.edtName.getText().toString());
            customer.setCount(Integer.parseInt(binding.edtCount.getText().toString()));
            customer.setDone(0);

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("customer", customer);
            intent.putExtra("TypeAdmin","SHELL");
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v->{
            startActivity(new Intent(this, LoginActivity.class));
        });

        binding.edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isName = true;
                }else {
                    isName = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isName && isCount){
                    binding.btnRegister.setEnabled(true);
                }else {
                    binding.btnRegister.setEnabled(false);
                }
            }
        });

        binding.edtCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().length() > 0){
                    isCount = true;
                }else {
                    isCount = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(isName && isCount){
                    binding.btnRegister.setEnabled(true);
                }else {
                    binding.btnRegister.setEnabled(false);
                }
            }
        });
    }
}