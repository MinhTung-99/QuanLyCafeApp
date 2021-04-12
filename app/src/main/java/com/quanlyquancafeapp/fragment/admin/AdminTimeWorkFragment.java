package com.quanlyquancafeapp.fragment.admin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminTimeWorkAdapter;
import com.quanlyquancafeapp.databinding.CustomPopupMenuBinding;
import com.quanlyquancafeapp.databinding.DialogAddTimeWorkBinding;
import com.quanlyquancafeapp.databinding.DialogDeleteTimeWorkBinding;
import com.quanlyquancafeapp.databinding.DialogUpdateTimeWorkBinding;
import com.quanlyquancafeapp.databinding.FragmentAdminTimeWorkBinding;
import com.quanlyquancafeapp.model.TimeWork;
import com.quanlyquancafeapp.presenter.admin.AdminTablePresenter;
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

        presenter = new AdminTimeWorkPresenter(getContext());
        adapter = new AdminTimeWorkAdapter(presenter.getTimeWork(), this);
        binding.rvTimeWork.setAdapter(adapter);

        initDialogAdd();
        initDialogUpdate();
        initDialogDelete();

        binding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
        });

        dialogAddTimeWorkBinding.btnCancel.setOnClickListener(v->alertDialogAdd.cancel());
        dialogAddTimeWorkBinding.btnYes.setOnClickListener(v->{
            TimeWork timeWork = new TimeWork();
            timeWork.setTimeStart(dialogAddTimeWorkBinding.edtTimeStart.getText().toString());
            timeWork.setTimeEnd(dialogAddTimeWorkBinding.edtTimeEnd.getText().toString());
            presenter.addTimeWork(timeWork);
            adapter.updateTimeWork(presenter.getTimeWork());
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
    public void menuUpdate(TimeWork timeWork) {
        dialogUpdateTimeWorkBinding.edtTimeStart.setText(timeWork.getTimeStart());
        dialogUpdateTimeWorkBinding.edtTimeEnd.setText(timeWork.getTimeEnd());

        alertDialogUpdate.show();

        dialogUpdateTimeWorkBinding.btnYes.setOnClickListener(v->{
            timeWork.setTimeStart(dialogUpdateTimeWorkBinding.edtTimeStart.getText().toString());
            timeWork.setTimeEnd(dialogUpdateTimeWorkBinding.edtTimeEnd.getText().toString());
            presenter.updateTimeWork(timeWork);
            adapter.updateTimeWork(presenter.getTimeWork());
            alertDialogUpdate.cancel();
        });
    }

    @Override
    public void menuDelete(TimeWork timeWork) {
        alertDialogDelete.show();

        dialogDeleteTimeWorkBinding.btnYes.setOnClickListener(v->{
            presenter.deleteTimeWork(timeWork.getIdTimeWork());
            adapter.updateTimeWork(presenter.getTimeWork());
            alertDialogDelete.cancel();
        });
    }
}
