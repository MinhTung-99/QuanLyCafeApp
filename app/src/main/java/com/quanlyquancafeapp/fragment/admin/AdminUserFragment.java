package com.quanlyquancafeapp.fragment.admin;

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
import com.quanlyquancafeapp.adapter.admin.AdminUserAdapter;
import com.quanlyquancafeapp.databinding.DialogAddUserBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteUserBinding;
import com.quanlyquancafeapp.databinding.DialogUpdateUserBinding;
import com.quanlyquancafeapp.databinding.FragmentAdminUserBinding;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.presenter.admin.AdminUserPresenter;
import com.quanlyquancafeapp.view.admin.IAdminUserView;
import java.util.ArrayList;

public class AdminUserFragment extends Fragment implements AdminUserAdapter.IRecyclerViewOnClick, IAdminUserView {
    private FragmentAdminUserBinding fragmentAdminUserBinding;
    private ArrayList<User> users;
    private AdminUserAdapter adapter;
    private AlertDialog alertDialogAdd;
    private AlertDialog alertDialogUpdate;
    private AlertDialog alertDialogDelete;
    private DialogAddUserBinding dialogAddUserBinding;
    private DialogUpdateUserBinding dialogUpdateUserBinding;
    private DialogDeleteUserBinding dialogDeleteUserBinding;
    private AdminUserPresenter adminUserPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddUserBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_user, container, false);
        dialogUpdateUserBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_update_user, container, false);
        dialogDeleteUserBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_user, container, false);
        fragmentAdminUserBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_user, container, false);
        return fragmentAdminUserBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adminUserPresenter = new AdminUserPresenter(this, getContext());
        initDiaLogAdd();
        initDiaLogUpdate();
        initDiaLogDelete();
        setAdapter();
        fragmentAdminUserBinding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
            setHintEdtDialogAdd();
        });
        onClickDialogAdd();
    }
    private void onClickDialogAdd(){
        dialogAddUserBinding.btnYes.setOnClickListener(v->{
            String gender = setGenderAdd();
            adminUserPresenter.addUserDB(
                    dialogAddUserBinding.edtUserName.getText().toString(), dialogAddUserBinding.edtPhoneNumber.getText().toString(),
                    dialogAddUserBinding.edtPassword.getText().toString(), gender);
            alertDialogAdd.cancel();
            adapter.updateUser(adminUserPresenter.getUsersDB());
        });
        dialogAddUserBinding.btnCancel.setOnClickListener(v->{
            alertDialogAdd.cancel();
        });
    }
    private void setAdapter(){
        users = adminUserPresenter.getUsersDB();
        adapter = new AdminUserAdapter(users, getContext(), this);
        fragmentAdminUserBinding.rvUser.setAdapter(adapter);
    }
    private String setGenderAdd(){
        if(dialogAddUserBinding.radioMan.isChecked()){
            return  "Nam";
        }else if(dialogAddUserBinding.radioWoman.isChecked()){
            return  "Nữ";
        }
        return "NULL";
    }
    private String setGenderUpdate(){
        if(dialogUpdateUserBinding.radioMan.isChecked()){
            return  "Nam";
        }else if(dialogUpdateUserBinding.radioWoman.isChecked()){
            return  "Nữ";
        }
        return "NULL";
    }
    @Override
    public void setHintEdtDialogAdd(){
        dialogAddUserBinding.txtToolbar.setText("Thêm nhân viên");
        dialogAddUserBinding.edtUserName.setHint("Tên nhân viên");
        dialogAddUserBinding.edtPhoneNumber.setHint("Số điện thoại");
        dialogAddUserBinding.edtPassword.setHint("Mật khẩu");
    }
    @Override
    public void setTextEdtDialogUpdate(User user){
        dialogUpdateUserBinding.txtToolbar.setText("Sửa nhân viên");
        dialogUpdateUserBinding.edtUserName.setText(user.getUserName());
        dialogUpdateUserBinding.edtPhoneNumber.setText(user.getPhoneNumber());
        dialogUpdateUserBinding.edtPassword.setText(user.getPassword());
    }
    @Override
    public void setRadioDialogUpdate(User user){
        if(user.getTypeUser().equals("USER")){
            setTextEdtDialogUpdate(user);
            if(user.getGender().equals("Nam")){
                dialogUpdateUserBinding.radioMan.setChecked(true);
                dialogUpdateUserBinding.radioWoman.setChecked(false);
            }else if(user.getGender().equals("Nữ")){
                dialogUpdateUserBinding.radioMan.setChecked(false);
                dialogUpdateUserBinding.radioWoman.setChecked(true);
            }
            alertDialogUpdate.show();
        }
    }
    private void onClickDialogUpdate(User user){
        dialogUpdateUserBinding.btnYes.setOnClickListener(v->{
            String gender = setGenderUpdate();
            adminUserPresenter.updateUserDB(user.getId(),
                    dialogUpdateUserBinding.edtUserName.getText().toString(), dialogUpdateUserBinding.edtPhoneNumber.getText().toString(),
                    dialogUpdateUserBinding.edtPassword.getText().toString(), gender);
            adapter.updateUser(adminUserPresenter.getUsersDB());
            alertDialogUpdate.cancel();
        });
        dialogUpdateUserBinding.btnCancel.setOnClickListener(v->{
            alertDialogUpdate.cancel();
        });
    }
    @Override
    public void initDiaLogAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogAddUserBinding.getRoot());
        alertDialogAdd = dialogBuilder.create();
        alertDialogAdd.setCancelable(false);
    }
    @Override
    public void initDiaLogUpdate() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogUpdateUserBinding.getRoot());
        alertDialogUpdate = dialogBuilder.create();
        alertDialogUpdate.setCancelable(false);
    }
    @Override
    public void initDiaLogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteUserBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
    }
    @Override
    public void onItemLongClick(User user) {
        setRadioDialogUpdate(user);
        setTextEdtDialogUpdate(user);
        onClickDialogUpdate(user);
    }
    @Override
    public void btnDeleteOnClick(User user) {
        alertDialogDelete.show();
        dialogDeleteUserBinding.txtToolbar.setText("Xoá nhân viên");
        dialogDeleteUserBinding.btnYes.setOnClickListener(v1->{
            adminUserPresenter.deleteUserDB(user.getId());
            alertDialogDelete.dismiss();
            adapter.updateUser(adminUserPresenter.getUsersDB());
            adapter.closeSwipe();
        });
    }
}
