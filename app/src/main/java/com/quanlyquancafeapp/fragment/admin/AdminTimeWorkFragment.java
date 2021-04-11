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
import com.quanlyquancafeapp.databinding.DialogAddTimeWorkBinding;
import com.quanlyquancafeapp.databinding.FragmentAdminTimeWorkBinding;
import com.quanlyquancafeapp.view.admin.IAdminTimeWorkView;

public class AdminTimeWorkFragment extends Fragment implements IAdminTimeWorkView {
    private FragmentAdminTimeWorkBinding binding;
    private DialogAddTimeWorkBinding dialogAddTimeWorkBinding;
    private AlertDialog alertDialogAdd;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogAddTimeWorkBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_add_time_work, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_time_work, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initDialogAdd();

        binding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
        });

        dialogAddTimeWorkBinding.btnCancel.setOnClickListener(v->alertDialogAdd.cancel());
    }

    @Override
    public void initDialogAdd() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogAddTimeWorkBinding.getRoot());
        alertDialogAdd = dialogBuilder.create();
        alertDialogAdd.setCancelable(false);
    }
}
