package com.quanlyquancafeapp.fragment.admin;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
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

    private boolean isTimeStart = true;
    private int clickBack = 0;
    private Integer hourOfDayStart,hourOfDayEnd;
    private Integer minuteStart, minuteEnd;

    private final String ADD_TIME_START_STR = "Thêm thời gian bắt đầu";
    private final String ADD_TIME_END_STR = "Thêm thời gian kết thúc";
    private final String UPDATE_TIME_START_STR = "Cập nhập gian bắt đầu";
    private final String UPDATE_TIME_END_STR = "Cập nhập thời gian kết thúc";
    private UserTime userTimeUpdate;

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

        presenter = new AdminTimeWorkPresenter(getContext(), this);
        adapter = new AdminTimeWorkAdapter(presenter.getUserTime(user.getId()), this);
        binding.rvTimeWork.setAdapter(adapter);

        initDialogAdd();
        initDialogUpdate();
        initDialogDelete();

        binding.imgAdd.setOnClickListener(v->{
            alertDialogAdd.show();
        });

        dialogAddTimeWorkBinding.btnCancel.setOnClickListener(v->{
            dialogAddTimeWorkBinding.btnAddTime.setEnabled(false);
            isTimeStart = true;
            clickBack = 0;
            presenter.restartDialogAdd(dialogAddTimeWorkBinding, ADD_TIME_START_STR);
            visibilityView(dialogAddTimeWorkBinding.imgBack, View.GONE);

            alertDialogAdd.dismiss();
        });

        dialogAddTimeWorkBinding.timePicker.setIs24HourView(true);
        dialogAddTimeWorkBinding.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(isTimeStart){
                    hourOfDayStart = hourOfDay;
                    minuteStart = minute;
                }else {
                    hourOfDayEnd = hourOfDay;
                    minuteEnd = minute;
                }
                dialogAddTimeWorkBinding.btnAddTime.setEnabled(true);
            }
        });
        dialogAddTimeWorkBinding.btnAddTime.setOnClickListener(v -> {
            dialogAddTimeWorkBinding.btnAddTime.setEnabled(false);

            isTimeStart = false;
            clickBack++;

            setTextBtn(dialogAddTimeWorkBinding.btnAddTime, ADD_TIME_END_STR);
            visibilityView(dialogAddTimeWorkBinding.imgBack, View.VISIBLE);
            if(clickBack == 2){
                visibilityView(dialogAddTimeWorkBinding.btnYes, View.VISIBLE);
                visibilityView(dialogAddTimeWorkBinding.btnAddTime, View.GONE);
            }
        });
        dialogAddTimeWorkBinding.imgBack.setOnClickListener(v->{
            if(clickBack == 1){
                isTimeStart = true;
                clickBack--;

                visibilityView(dialogAddTimeWorkBinding.imgBack, View.GONE);
                visibilityView(dialogAddTimeWorkBinding.btnAddTime, View.VISIBLE);
                setTextBtn(dialogAddTimeWorkBinding.btnAddTime, ADD_TIME_START_STR);
            }else if(clickBack == 2){
                isTimeStart = false;
                clickBack--;

                visibilityView(dialogAddTimeWorkBinding.btnYes, View.GONE);
                visibilityView(dialogAddTimeWorkBinding.btnAddTime, View.VISIBLE);
                setTextBtn(dialogAddTimeWorkBinding.btnAddTime, ADD_TIME_END_STR);
            }
        });
        dialogAddTimeWorkBinding.btnYes.setOnClickListener(v->{
            UserTime userTime = new UserTime();
            userTime.setId(user.getId());

            presenter.setUserTimeStart(hourOfDayStart, minuteStart, userTime);
            presenter.setUserTimeEnd(hourOfDayEnd, minuteEnd, userTime);

            isTimeStart = true;
            clickBack = 0;
            presenter.restartDialogUpdate(dialogUpdateTimeWorkBinding, ADD_TIME_START_STR, userTime);
            visibilityView(dialogAddTimeWorkBinding.imgBack, View.GONE);

            presenter.addTimeWork(userTime);

            adapter.updateTimeWork(presenter.getUserTime(user.getId()));

            alertDialogAdd.dismiss();
        });

        dialogDeleteTimeWorkBinding.btnCancel.setOnClickListener(v->alertDialogDelete.cancel());

        // UPDATE_DIALOG
        dialogUpdateTimeWorkBinding.timePicker.setIs24HourView(true);
        dialogUpdateTimeWorkBinding.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if(isTimeStart){
                    hourOfDayStart = hourOfDay;
                    minuteStart = minute;
                }else {
                    hourOfDayEnd = hourOfDay;
                    minuteEnd = minute;
                }
                dialogUpdateTimeWorkBinding.btnAddTime.setEnabled(true);
            }
        });

        dialogUpdateTimeWorkBinding.btnAddTime.setOnClickListener(v -> {
            isTimeStart = false;
            clickBack++;

            setTextBtn(dialogUpdateTimeWorkBinding.btnAddTime, UPDATE_TIME_END_STR);
            visibilityView(dialogUpdateTimeWorkBinding.imgBack, View.VISIBLE);
            if(clickBack == 2){
                dialogUpdateTimeWorkBinding.txtTime.setText(hourOfDayStart+":"+minuteStart +"->"+hourOfDayEnd+":"+minuteEnd);
                visibilityView(dialogUpdateTimeWorkBinding.btnYes, View.VISIBLE);
                visibilityView(dialogUpdateTimeWorkBinding.btnAddTime, View.GONE);
                visibilityView(dialogUpdateTimeWorkBinding.txtTime, View.VISIBLE);

                visibilityTimePicker(dialogUpdateTimeWorkBinding.timePicker, View.GONE);
            }else if(clickBack == 1){
                dialogUpdateTimeWorkBinding.timePicker.setHour(Integer.parseInt(userTimeUpdate.getTimeEnd().substring(0,2)));
                dialogUpdateTimeWorkBinding.timePicker.setMinute(Integer.parseInt(userTimeUpdate.getTimeEnd().substring(3,5)));
            }

            dialogUpdateTimeWorkBinding.btnAddTime.setEnabled(false);
        });

        dialogUpdateTimeWorkBinding.btnCancel.setOnClickListener(v->{
            dialogUpdateTimeWorkBinding.btnAddTime.setEnabled(false);

            isTimeStart = true;
            clickBack = 0;
            presenter.restartDialogUpdate(dialogUpdateTimeWorkBinding, UPDATE_TIME_START_STR, userTimeUpdate);
            visibilityView(dialogUpdateTimeWorkBinding.imgBack, View.GONE);

            alertDialogUpdate.dismiss();
        });

        dialogUpdateTimeWorkBinding.imgBack.setOnClickListener(v->{
            visibilityTimePicker(dialogUpdateTimeWorkBinding.timePicker, View.VISIBLE);

            if(clickBack == 1){
                isTimeStart = true;
                clickBack--;

                visibilityView(dialogUpdateTimeWorkBinding.imgBack, View.GONE);
                visibilityView(dialogUpdateTimeWorkBinding.btnAddTime, View.VISIBLE);
                setTextBtn(dialogUpdateTimeWorkBinding.btnAddTime, UPDATE_TIME_START_STR);

                dialogUpdateTimeWorkBinding.timePicker.setHour(Integer.parseInt(userTimeUpdate.getTimeStart().substring(0,2)));
                dialogUpdateTimeWorkBinding.timePicker.setMinute(Integer.parseInt(userTimeUpdate.getTimeStart().substring(3,5)));
            }else if(clickBack == 2){
                isTimeStart = false;
                clickBack--;

                visibilityView(dialogUpdateTimeWorkBinding.btnYes, View.GONE);
                visibilityView(dialogUpdateTimeWorkBinding.txtTime, View.GONE);
                visibilityView(dialogUpdateTimeWorkBinding.btnAddTime, View.VISIBLE);
                setTextBtn(dialogUpdateTimeWorkBinding.btnAddTime, UPDATE_TIME_END_STR);

                dialogUpdateTimeWorkBinding.timePicker.setHour(Integer.parseInt(userTimeUpdate.getTimeEnd().substring(0,2)));
                dialogUpdateTimeWorkBinding.timePicker.setMinute(Integer.parseInt(userTimeUpdate.getTimeEnd().substring(3,5)));
            }
        });

        dialogUpdateTimeWorkBinding.btnYes.setOnClickListener(v->{

            presenter.setUserTimeStart(hourOfDayStart, minuteStart, userTimeUpdate);
            presenter.setUserTimeEnd(hourOfDayEnd, minuteEnd, userTimeUpdate);

            isTimeStart = true;
            clickBack = 0;
            presenter.restartDialogUpdate(dialogUpdateTimeWorkBinding, UPDATE_TIME_START_STR, userTimeUpdate);
            visibilityView(dialogUpdateTimeWorkBinding.imgBack, View.GONE);

            presenter.updateUserTime(userTimeUpdate);
            adapter.updateTimeWork(presenter.getUserTime(user.getId()));

            alertDialogUpdate.dismiss();
        });
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
    public void visibilityView(View view, int isVisibility) {
        view.setVisibility(isVisibility);
    }

    @Override
    public void setTextBtn(Button button, String text) {
        button.setText(text);
    }

    @Override
    public void visibilityTimePicker(TimePicker timePicker, int visibility) {
        timePicker.setVisibility(visibility);
    }

    @Override
    public void setTimePicker(TimePicker timePicker, Integer hourOfDay, Integer minute) {
        timePicker.setHour(hourOfDay);
        timePicker.setMinute(minute);
    }

    @Override
    public void menuUpdate(UserTime userTime) {
        userTimeUpdate = userTime;

        alertDialogUpdate.show();

        isTimeStart = true;
        clickBack = 0;

        dialogUpdateTimeWorkBinding.timePicker.setHour(Integer.parseInt(userTime.getTimeStart().substring(0,2)));
        dialogUpdateTimeWorkBinding.timePicker.setMinute(Integer.parseInt(userTime.getTimeStart().substring(3,5)));

        dialogUpdateTimeWorkBinding.btnAddTime.setEnabled(false);
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
