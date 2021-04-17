package com.quanlyquancafeapp.view.admin;

import android.view.View;
import android.widget.Button;

public interface IAdminTimeWorkView {
    void initDialogAdd();
    void initDialogUpdate();
    void initDialogDelete();
    void visibilityView(View view, int isVisibility);
    void setTextBtn(Button button, String text);
}
