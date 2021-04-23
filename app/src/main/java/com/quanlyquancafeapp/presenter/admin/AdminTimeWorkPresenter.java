package com.quanlyquancafeapp.presenter.admin;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import com.quanlyquancafeapp.databinding.DialogAddTimeWorkBinding;
import com.quanlyquancafeapp.databinding.DialogUpdateTimeWorkBinding;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.db.UserTimeTable;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.model.UserTime;
import com.quanlyquancafeapp.view.admin.IAdminTimeWorkView;

import java.util.ArrayList;

public class AdminTimeWorkPresenter {

    private IAdminTimeWorkView iAdminTimeWorkView;

    public AdminTimeWorkPresenter(Context context, IAdminTimeWorkView iAdminTimeWorkView) {
        DatabaseHelper db = new DatabaseHelper(context);
        this.iAdminTimeWorkView = iAdminTimeWorkView;
    }

    public AdminTimeWorkPresenter() {
    }

    public void addTimeWork(UserTime userTime){
        try {
            UserTimeTable.addUserTime(userTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<UserTime> getUserTime(Long idUser){
        return UserTimeTable.getUserTime(idUser);
    }
    public int updateUserTime(UserTime userTime){
        return UserTimeTable.updateUserTime(userTime);
    }
    public int deleteTimeWork(Long id){
        return UserTimeTable.deleteUserTime(id);
    }

    public void restartDialogUpdate(DialogUpdateTimeWorkBinding dialogUpdateTimeWorkBinding, String text, UserTime userTime){

        iAdminTimeWorkView.visibilityView(dialogUpdateTimeWorkBinding.btnYes, View.GONE);
        iAdminTimeWorkView.visibilityView(dialogUpdateTimeWorkBinding.txtTime, View.GONE);
        iAdminTimeWorkView.visibilityView(dialogUpdateTimeWorkBinding.btnAddTime, View.VISIBLE);
        iAdminTimeWorkView.setTextBtn(dialogUpdateTimeWorkBinding.btnAddTime, text);

        iAdminTimeWorkView.visibilityTimePicker(dialogUpdateTimeWorkBinding.timePicker, View.VISIBLE);

        iAdminTimeWorkView.setTimePicker(
                dialogUpdateTimeWorkBinding.timePicker,
                Integer.parseInt(userTime.getTimeEnd().substring(0,2)),
                Integer.parseInt(userTime.getTimeEnd().substring(3,5))
        );
    }

    public void restartDialogAdd(DialogAddTimeWorkBinding dialogAddTimeWorkBinding, String text){
        iAdminTimeWorkView.visibilityView(dialogAddTimeWorkBinding.btnYes, View.GONE);
        iAdminTimeWorkView.visibilityView(dialogAddTimeWorkBinding.txtTime, View.GONE);
        iAdminTimeWorkView.visibilityView(dialogAddTimeWorkBinding.btnAddTime, View.VISIBLE);
        iAdminTimeWorkView.setTextBtn(dialogAddTimeWorkBinding.btnAddTime, text);

        iAdminTimeWorkView.visibilityTimePicker(dialogAddTimeWorkBinding.timePicker, View.VISIBLE);
    }

    public void setUserTimeStart(Integer hourOfDayStart, Integer minuteStart, UserTime userTime){
        if(hourOfDayStart < 10 && minuteStart < 10){
            userTime.setTimeStart("0"+hourOfDayStart + ":" + "0" + minuteStart);
        }else if(hourOfDayStart < 10 && minuteStart >= 10) {
            userTime.setTimeStart("0"+hourOfDayStart + ":" + minuteStart);
        }else if(hourOfDayStart >= 10 && minuteStart < 10){
            userTime.setTimeStart(hourOfDayStart + ":" + "0" + minuteStart);
        }else {
            userTime.setTimeStart(hourOfDayStart + ":" + minuteStart);
        }
    }

    public void setUserTimeEnd(Integer hourOfDayEnd, Integer minuteEnd, UserTime userTime){
        if(hourOfDayEnd < 10 && minuteEnd < 10){
            userTime.setTimeEnd("0"+hourOfDayEnd + ":" + "0" + minuteEnd);
        }else if(hourOfDayEnd < 10 && minuteEnd >= 10) {
            userTime.setTimeEnd("0"+hourOfDayEnd + ":" + minuteEnd);
        }else if(hourOfDayEnd >= 10 && minuteEnd < 10){
            userTime.setTimeEnd(hourOfDayEnd + ":" + "0" + minuteEnd);
        }else {
            userTime.setTimeEnd(hourOfDayEnd + ":" + minuteEnd);
        }
    }
}
