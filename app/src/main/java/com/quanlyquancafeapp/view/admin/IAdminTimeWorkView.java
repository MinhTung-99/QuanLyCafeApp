package com.quanlyquancafeapp.view.admin;

import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public interface IAdminTimeWorkView {
    void initDialogAdd();
    void initDialogUpdate();
    void initDialogDelete();
    void visibilityView(View view, int isVisibility);
    void setTextBtn(Button button, String text);
    void visibilityTimePicker(TimePicker timePicker, int visibility);
    void setTimePicker(TimePicker timePicker,Integer hourOfDay, Integer minute);
}
