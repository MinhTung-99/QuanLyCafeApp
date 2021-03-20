package com.quanlyquancafeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.quanlyquancafeapp.databinding.ActivityLoginBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.Invoice;
import com.quanlyquancafeapp.presenter.LoginPresenter;
import com.quanlyquancafeapp.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private ActivityLoginBinding binding;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginPresenter = new LoginPresenter(this);
        setBackgroundButton();
        binding.btnRegister.setOnClickListener(v -> loginPresenter.navigateToRegisterActivity());
        binding.txtForgotPassword.setOnClickListener(v -> loginPresenter.navigateToForgotPasswordActivity());
        binding.btnLogin.setOnClickListener(v -> {
            String typeUser = loginPresenter.handleLogin(this,
                    binding.edtAccount.getText().toString(), binding.edtPassword.getText().toString());
            if(typeUser.equals("ADMIN")){
                loginPresenter.navigateToLogInToHomeAdminActivity();
                finish();
            }else if(typeUser.equals("USER")){
                loginPresenter.navigateToHomeActivity();
                finish();
            }
        });

//        DatabaseHelper db = new DatabaseHelper(this);
//        Invoice invoice = new Invoice(1L,1L,500L,1L,
//                23,100,100,"20/10/2020",1);
//        try {
//            db.addInvoice(invoice);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Toast.makeText(LoginActivity.this, db.getInvoices().size()+" ==", Toast.LENGTH_SHORT).show();
    }
    private void setBackgroundButton() {
        binding.btnLogin.setBackgroundResource(R.drawable.rounded_white);
        binding.btnRegister.setBackgroundResource(R.drawable.rounded_white);
    }
    @Override
    public void navigateToRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    @Override
    public void navigateToForgotPasswordActivity() {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
    @Override
    public void navigateToHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
    @Override
    public void navigateToHomeAdminActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
        startActivity(intent);
    }
    @Override
    public void loginFail() {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.TOP, 0 , 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.root));
        TextView txtToast = view.findViewById(R.id.txt_toast);
        txtToast.setText("Tài khoản hoặc mật khẩu không đúng!!!");
        toast.setView(view);
        toast.show();
    }
    @Override
    public void hideView() {
        binding.btnRegister.setVisibility(View.GONE);
    }
    @Override
    public void showView() {
        binding.btnRegister.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.visibilityView(this);
    }
}