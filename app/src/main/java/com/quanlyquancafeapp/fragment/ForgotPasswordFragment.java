package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.presenter.ForgotPasswordPresenter;
import com.quanlyquancafeapp.view.IForgotPasswordView;

public class ForgotPasswordFragment extends Fragment implements IForgotPasswordView {
    private ForgotPasswordPresenter forgotPasswordPresenter;
    private Button btnGetPassword;
    private ImageView imgBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        btnGetPassword.setOnClickListener(v->forgotPasswordPresenter.getPassword());
        imgBack.setOnClickListener(v->forgotPasswordPresenter.back());
    }
    private void initView() {
        forgotPasswordPresenter = new ForgotPasswordPresenter(this);
        btnGetPassword = getView().findViewById(R.id.btn_get_password);
        imgBack = getView().findViewById(R.id.img_back);
    }
    @Override
    public void navigateNewPasswordFragment() {
        Navigation.findNavController(getView()).navigate(R.id.newPasswordFragment);
    }
    @Override
    public void backToLoginFragment() {
        Navigation.findNavController(getView()).popBackStack();
    }
}
