package com.quanlyquancafeapp.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.UserAdapter;
import com.quanlyquancafeapp.databinding.DialogAddBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteBinding;
import com.quanlyquancafeapp.databinding.DialogUpdateBinding;
import com.quanlyquancafeapp.databinding.FragmentUserBinding;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.presenter.UserPresenter;
import com.quanlyquancafeapp.view.IUserView;
import java.util.ArrayList;

public class UserFragment extends Fragment implements UserAdapter.IRecyclerViewOnClick, IUserView {
    private FragmentUserBinding binding;
    private ArrayList<User> users;
    private UserAdapter adapter;
    private AlertDialog alertDialogAdd;
    private AlertDialog alertDialogUpdate;
    private AlertDialog alertDialogDelete;
    private DialogAddBinding dialogAddBinding;
    private DialogUpdateBinding dialogUpdateBinding;
    private DialogDeleteBinding dialogDeleteBinding;
    private UserPresenter userPresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add, container, false);
        dialogUpdateBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_update, container, false);
        dialogDeleteBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userPresenter = new UserPresenter(this, getContext());
        initDiaLogAdd();
        initDiaLogUpdate();
        initDiaLogDelete();
        setAdapter();
        binding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
            setHintEdtDialogAdd();
        });
        onClickDialogAdd();
    }
    private void onClickDialogAdd(){
        dialogAddBinding.btnYes.setOnClickListener(v->{
            String gender = setGenderAdd();
            userPresenter.addUserDB(
                    dialogAddBinding.edtUserName.getText().toString(), dialogAddBinding.edtPhoneNumber.getText().toString(),
                    dialogAddBinding.edtPassword.getText().toString(), gender);
            alertDialogAdd.cancel();
            adapter.updateUser(userPresenter.getUsersDB());
        });
        dialogAddBinding.btnCancel.setOnClickListener(v->{
            alertDialogAdd.cancel();
        });
    }
    private void setAdapter(){
        users = userPresenter.getUsersDB();
        adapter = new UserAdapter(users, getContext(), this);
        binding.rvUser.setAdapter(adapter);
    }
    private String setGenderAdd(){
        if(dialogAddBinding.radioMan.isChecked()){
            return  "Nam";
        }else if(dialogAddBinding.radioWoman.isChecked()){
            return  "Nữ";
        }
        return "NULL";
    }
    private String setGenderUpdate(){
        if(dialogUpdateBinding.radioMan.isChecked()){
            return  "Nam";
        }else if(dialogUpdateBinding.radioWoman.isChecked()){
            return  "Nữ";
        }
        return "NULL";
    }
    @Override
    public void setHintEdtDialogAdd(){
        dialogAddBinding.txtToolbar.setText("Thêm nhân viên");
        dialogAddBinding.edtUserName.setHint("Tên nhân viên");
        dialogAddBinding.edtPhoneNumber.setHint("Số điện thoại");
        dialogAddBinding.edtPassword.setHint("Mật khẩu");
    }
    @Override
    public void setTextEdtDialogUpdate(User user){
        dialogUpdateBinding.txtToolbar.setText("Sửa nhân viên");
        dialogUpdateBinding.edtUserName.setText(user.getUserName());
        dialogUpdateBinding.edtPhoneNumber.setText(user.getPhoneNumber());
        dialogUpdateBinding.edtPassword.setText(user.getPassword());
    }
    @Override
    public void setRadioDialogUpdate(User user){
        if(user.getTypeUser().equals("USER")){
            setTextEdtDialogUpdate(user);
            if(user.getGender().equals("Nam")){
                dialogUpdateBinding.radioMan.setChecked(true);
                dialogUpdateBinding.radioWoman.setChecked(false);
            }else if(user.getGender().equals("Nữ")){
                dialogUpdateBinding.radioMan.setChecked(false);
                dialogUpdateBinding.radioWoman.setChecked(true);
            }
            alertDialogUpdate.show();
        }
    }
    private void onClickDialogUpdate(User user){
        dialogUpdateBinding.btnYes.setOnClickListener(v->{
            String gender = setGenderUpdate();
            userPresenter.updateUserDB(user.getId(),
                    dialogUpdateBinding.edtUserName.getText().toString(), dialogUpdateBinding.edtPhoneNumber.getText().toString(),
                    dialogUpdateBinding.edtPassword.getText().toString(), gender);
            adapter.updateUser(userPresenter.getUsersDB());
            alertDialogUpdate.cancel();
        });
        dialogUpdateBinding.btnCancel.setOnClickListener(v->{
            alertDialogUpdate.cancel();
        });
    }
    @Override
    public void initDiaLogAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogAddBinding.getRoot());
        alertDialogAdd = dialogBuilder.create();
        alertDialogAdd.setCancelable(false);
    }
    @Override
    public void initDiaLogUpdate() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogUpdateBinding.getRoot());
        alertDialogUpdate = dialogBuilder.create();
        alertDialogUpdate.setCancelable(false);
    }
    @Override
    public void initDiaLogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
    }
    @Override
    public void onItemLongClick(User user) {
        setRadioDialogUpdate(user);
        setTextEdtDialogUpdate(user);
        onClickDialogUpdate(user);
    }
    @Override
    public void btnDeleteOnClick(int position) {
        alertDialogDelete.show();
        dialogDeleteBinding.txtToolbar.setText("Xoá nhân viên");
        dialogDeleteBinding.btnYes.setOnClickListener(v1->{
            userPresenter.deleteUserDB(users.get(position).getId());
            alertDialogDelete.cancel();
            adapter.updateUser(userPresenter.getUsersDB());
            adapter.closeSwipe();
        });
    }
}
