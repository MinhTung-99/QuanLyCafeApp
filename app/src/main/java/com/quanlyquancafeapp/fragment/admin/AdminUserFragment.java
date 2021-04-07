package com.quanlyquancafeapp.fragment.admin;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private User user;
    private OutputStream outputStream;
    private boolean isNewProfile;

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
        user = new User();
        initDiaLogAdd();
        initDiaLogUpdate();
        initDiaLogDelete();
        setAdapter();
        fragmentAdminUserBinding.imgAdd.setOnClickListener(v->{
            emptyDialogAdd();
            alertDialogAdd.show();
            setHintEdtDialogAdd();
        });
        onClickDialogAdd();
        dialogAddUserBinding.imgProfile.setOnClickListener(v->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        dialogUpdateUserBinding.imgProfile.setOnClickListener(v->{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
    }
    private void onClickDialogAdd(){
        dialogAddUserBinding.btnYes.setOnClickListener(v->{
            String gender = setGenderAdd();
            user.setUserName(dialogAddUserBinding.edtUserName.getText().toString());
            user.setPhoneNumber(dialogAddUserBinding.edtPhoneNumber.getText().toString());
            user.setPassword(dialogAddUserBinding.edtPassword.getText().toString());
            user.setGender(gender);
            user.setTypeUser("USER");
            adminUserPresenter.addUserDB(user);
            alertDialogAdd.cancel();
            adapter.updateUser(adminUserPresenter.getUsersDB());
        });
        dialogAddUserBinding.btnCancel.setOnClickListener(v->{
            alertDialogAdd.cancel();
        });
    }
    private void setAdapter(){
        users = adminUserPresenter.getUsersDB();
        adapter = new AdminUserAdapter(users, this);
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
        dialogUpdateUserBinding.imgProfile.setImageBitmap(BitmapFactory.decodeFile(user.getFilePath()));
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

    @Override
    public void emptyDialogAdd() {
        dialogAddUserBinding.edtUserName.setText("");
        dialogAddUserBinding.edtPhoneNumber.setText("");
        dialogAddUserBinding.edtPassword.setText("");
        dialogAddUserBinding.radioMan.setChecked(false);
        dialogAddUserBinding.radioWoman.setChecked(false);
        dialogAddUserBinding.imgProfile.setImageResource(R.drawable.ic_profile);
    }

    private void onClickDialogUpdate(User user){
        dialogUpdateUserBinding.btnYes.setOnClickListener(v->{
            String gender = setGenderUpdate();
            user.setUserName(dialogAddUserBinding.edtUserName.getText().toString());
            user.setPhoneNumber(dialogAddUserBinding.edtPhoneNumber.getText().toString());
            user.setPassword(dialogAddUserBinding.edtPassword.getText().toString());
            user.setGender(gender);
            user.setTypeUser("USER");

            this.user.setId(user.getId());
            this.user.setUserName(dialogUpdateUserBinding.edtUserName.getText().toString());
            this.user.setPhoneNumber(dialogUpdateUserBinding.edtPhoneNumber.getText().toString());
            this.user.setPassword(dialogUpdateUserBinding.edtPassword.getText().toString());
            this.user.setGender(gender);
            this.user.setTypeUser("USER");
            if(!isNewProfile){
                this.user.setFilePath(user.getFilePath());
            }
            adminUserPresenter.updateUserDB(this.user);
            adapter.updateUser(adminUserPresenter.getUsersDB());
            alertDialogUpdate.cancel();
        });
        dialogUpdateUserBinding.btnCancel.setOnClickListener(v->{
            alertDialogUpdate.cancel();
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            isNewProfile = true;
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            dialogAddUserBinding.imgProfile.setImageBitmap(bitmap);
            dialogUpdateUserBinding.imgProfile.setImageBitmap(bitmap);
            File file = saveToInternalStorage(bitmap);
            this.user.setFilePath(file.getPath());
        }
    }
    private File saveToInternalStorage(Bitmap image) {
        File myDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File dir = new File(myDir.getAbsolutePath() + "/picture");
        dir.mkdir();
        File file = new File(dir, System.currentTimeMillis() + ".jpg");

        try {
            outputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return file;
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
        });
        dialogDeleteUserBinding.btnCancel.setOnClickListener(v->alertDialogDelete.cancel());
    }
}
