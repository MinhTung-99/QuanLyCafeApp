package com.quanlyquancafeapp.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.UserAdapter;
import com.quanlyquancafeapp.databinding.DialogAddOrFixBinding;
import com.quanlyquancafeapp.databinding.FragmentUserBinding;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.utils.DataFake;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class UserFragment extends Fragment implements IRecyclerViewOnItemClick {
    private FragmentUserBinding binding;
    private ArrayList<User> users;
    private UserAdapter adapter;
    private AlertDialog alertDialog;
    private DialogAddOrFixBinding dialogAddOrFixBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddOrFixBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_or_fix, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        users = DataFake.userFake();
        initDialog();
        adapter = new UserAdapter(users, getContext(), this);
        binding.rvUser.setAdapter(adapter);

        binding.imgAdd.setOnClickListener(v->{
            Log.d("KMFG","CLICKED");
            alertDialog.show();

            dialogAddOrFixBinding.txtToolbar.setText("Thêm nhân viên");
            dialogAddOrFixBinding.edtUserName.setHint("Tên nhân viên");
            dialogAddOrFixBinding.edtPhoneNumber.setHint("Số điện thoại");
            dialogAddOrFixBinding.edtPassword.setHint("Mật khẩu");
        });
    }
    private void initDialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogAddOrFixBinding.getRoot());
        alertDialog = dialogBuilder.create();
    }
    @Override
    public void onClick(Object model) {
        alertDialog.show();

        User user = (User) model;
        dialogAddOrFixBinding.txtToolbar.setText("Thêm nhân viên");
        dialogAddOrFixBinding.edtUserName.setText(user.getUserName());
        dialogAddOrFixBinding.edtPhoneNumber.setText(user.getPhoneNumber());
        dialogAddOrFixBinding.edtPassword.setText(user.getPassword());
    }

    @Override
    public void reductionBtn(int position) { }
}
