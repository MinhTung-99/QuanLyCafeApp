package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
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
import com.quanlyquancafeapp.presenter.NewPasswordPresenter;
import com.quanlyquancafeapp.view.INewPasswordView;

public class NewPasswordFragment extends Fragment implements INewPasswordView {
    private NewPasswordPresenter newPasswordPresenter;
    private ImageView imgBack;
    private Button btnChangePassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_password, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        imgBack.setOnClickListener(v->newPasswordPresenter.backToForgotPassword());
        btnChangePassword.setOnClickListener(v->newPasswordPresenter.backToLogin());
    }
    private void initView() {
        newPasswordPresenter = new NewPasswordPresenter(this);
        imgBack = getView().findViewById(R.id.img_back);
        btnChangePassword = getView().findViewById(R.id.btn_change_password);
    }
    @Override
    public void backToForgotPasswordFragment() {
        Navigation.findNavController(getView()).popBackStack();
    }
    @Override
    public void backToLoginFragment() {
        for(int i = 0; i < 2; i++)
            Navigation.findNavController(getView()).popBackStack();
    }
}
