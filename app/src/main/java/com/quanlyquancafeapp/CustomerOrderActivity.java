package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.quanlyquancafeapp.databinding.ActivityCustomerOrderBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Customer;

public class CustomerOrderActivity extends AppCompatActivity {
    private ActivityCustomerOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_order);

        binding.btnLogin.setBackground(getDrawable(R.drawable.rounded_white));
        binding.btnRegister.setBackground(getDrawable(R.drawable.rounded_white));

        DatabaseHelper db = new DatabaseHelper(this);
        binding.btnRegister.setOnClickListener(v->{
            Customer customer = new Customer();
            customer.setName(binding.edtName.getText().toString());
            customer.setCount(Integer.parseInt(binding.edtCount.getText().toString()));
            customer.setDone(0);
            try {
                db.addCustomer(customer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("TypeAdmin","SHELL");
            startActivity(intent);
        });

        binding.btnLogin.setOnClickListener(v->{
            startActivity(new Intent(this, LoginActivity.class));
        });
    }
}