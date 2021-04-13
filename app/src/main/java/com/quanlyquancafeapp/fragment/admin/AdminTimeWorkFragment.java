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
import com.quanlyquancafeapp.adapter.admin.AdminTimeWorkAdapter;
import com.quanlyquancafeapp.databinding.DialogAddTimeWorkBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteTimeWorkBinding;
import com.quanlyquancafeapp.databinding.DialogUpdateTimeWorkBinding;
import com.quanlyquancafeapp.databinding.FragmentAdminTimeWorkBinding;
import com.quanlyquancafeapp.model.TimeWork;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.model.UserTime;
import com.quanlyquancafeapp.presenter.admin.AdminTimeWorkPresenter;
import com.quanlyquancafeapp.view.admin.IAdminTimeWorkView;

public class AdminTimeWorkFragment extends Fragment implements IAdminTimeWorkView, AdminTimeWorkAdapter.IRecyclerViewItemOnclick {
    private FragmentAdminTimeWorkBinding binding;
    private DialogAddTimeWorkBinding dialogAddTimeWorkBinding;
    private DialogUpdateTimeWorkBinding dialogUpdateTimeWorkBinding;
    private DialogDeleteTimeWorkBinding dialogDeleteTimeWorkBinding;
    private AlertDialog alertDialogAdd;
    private AlertDialog alertDialogUpdate;
    private AlertDialog alertDialogDelete;
    private AdminTimeWorkPresenter presenter;
    private AdminTimeWorkAdapter adapter;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddTimeWorkBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_time_work, container, false);
        dialogUpdateTimeWorkBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_update_time_work, container, false);
        dialogDeleteTimeWorkBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_time_work, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_time_work, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = (User) getArguments().getSerializable("user");

        presenter = new AdminTimeWorkPresenter(getContext());
        adapter = new AdminTimeWorkAdapter(presenter.getUserTime(user.getId()), this);
        binding.rvTimeWork.setAdapter(adapter);

        initDialogAdd();
        initDialogUpdate();
        initDialogDelete();

        binding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
        });

        dialogAddTimeWorkBinding.btnCancel.setOnClickListener(v->alertDialogAdd.cancel());
        dialogAddTimeWorkBinding.btnYes.setOnClickListener(v->{
            UserTime userTime = new UserTime();
            userTime.setId(user.getId());
            userTime.setTimeStart(dialogAddTimeWorkBinding.edtTimeStart.getText().toString());
            userTime.setTimeEnd(dialogAddTimeWorkBinding.edtTimeEnd.getText().toString());
            presenter.addTimeWork(userTime);
            adapter.updateTimeWork(presenter.getUserTime(user.getId()));
            alertDialogAdd.cancel();
        });

        dialogUpdateTimeWorkBinding.btnCancel.setOnClickListener(v->alertDialogUpdate.cancel());

        dialogDeleteTimeWorkBinding.btnCancel.setOnClickListener(v->alertDialogDelete.cancel());
    }

    @Override
    public void initDialogAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogAddTimeWorkBinding.getRoot());
        alertDialogAdd = dialogBuilder.create();
        alertDialogAdd.setCancelable(false);
    }
    @Override
    public void initDialogUpdate() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogUpdateTimeWorkBinding.getRoot());
        alertDialogUpdate = dialogBuilder.create();
        alertDialogUpdate.setCancelable(false);
    }
    @Override
    public void initDialogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteTimeWorkBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
        alertDialogDelete.setCancelable(false);
    }

    @Override
    public void menuUpdate(UserTime userTime) {
        dialogUpdateTimeWorkBinding.edtTimeStart.setText(userTime.getTimeStart());
        dialogUpdateTimeWorkBinding.edtTimeEnd.setText(userTime.getTimeEnd());

        alertDialogUpdate.show();

        dialogUpdateTimeWorkBinding.btnYes.setOnClickListener(v->{
            userTime.setTimeStart(dialogUpdateTimeWorkBinding.edtTimeStart.getText().toString());
            userTime.setTimeEnd(dialogUpdateTimeWorkBinding.edtTimeEnd.getText().toString());
            presenter.updateUserTime(userTime);
            adapter.updateTimeWork(presenter.getUserTime(user.getId()));
            alertDialogUpdate.cancel();
        });
    }

    @Override
    public void menuDelete(UserTime userTime) {
        alertDialogDelete.show();

        dialogDeleteTimeWorkBinding.btnYes.setOnClickListener(v->{
            presenter.deleteTimeWork(userTime.getIdUserTime());
            adapter.updateTimeWork(presenter.getUserTime(user.getId()));
            alertDialogDelete.cancel();
        });
    }
}
