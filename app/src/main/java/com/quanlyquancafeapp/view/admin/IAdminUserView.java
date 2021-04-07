package com.quanlyquancafeapp.view.admin;

import com.quanlyquancafeapp.model.User;

public interface IAdminUserView {
    void initDiaLogAdd();
    void initDiaLogUpdate();
    void initDiaLogDelete();
    void setHintEdtDialogAdd();
    void setTextEdtDialogUpdate(User user);
    void setRadioDialogUpdate(User user);
    void emptyDialogAdd();
}
