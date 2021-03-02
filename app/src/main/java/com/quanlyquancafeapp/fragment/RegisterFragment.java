package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.presenter.RegisterPresenter;
import com.quanlyquancafeapp.view.IRegisterView;

public class RegisterFragment extends Fragment implements IRegisterView {
    private RegisterPresenter registerPresenter;
    private Button btnRegister;
    private ImageView imgBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        btnRegister.setBackgroundResource(R.drawable.rounded_white);
        btnRegister.setOnClickListener(v->registerPresenter.register());
        imgBack.setOnClickListener(v->registerPresenter.register());
    }
    private void initView() {
        registerPresenter = new RegisterPresenter(this);
        btnRegister = getView().findViewById(R.id.btn_register);
        imgBack = getView().findViewById(R.id.img_back);
    }
    @Override
    public void navigateToLoginFragment() {
        Navigation.findNavController(getView()).popBackStack();
    }
}
