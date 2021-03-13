package com.quanlyquancafeapp.view;

import com.quanlyquancafeapp.model.User;

public interface IUserView {
    void initDiaLogAdd();
    void initDiaLogUpdate();
    void initDiaLogDelete();
    void setHintEdtDialogAdd();
    void setTextEdtDialogUpdate(User user);
    void setRadioDialogUpdate(User user);
}
