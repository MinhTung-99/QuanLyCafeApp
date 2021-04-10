package com.quanlyquancafeapp.presenter;

import android.content.Context;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import java.util.ArrayList;

public class NewPasswordPresenter {
    private DatabaseHelper db;

    public NewPasswordPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public void updatePassword(User user){
        db.updateUser(user);
    }
    public User getUserAdmin(){
        ArrayList<User> users = db.getUsers();
        User user = null;
        for(User u: users){
            if(u.getTypeUser().equals("ADMIN")){
                user = u;
                break;
            }
        }
        return user;
    }
    public boolean isSamePassword(String newPassword, String newPasswordAgain){
        if(newPassword.equals(newPasswordAgain)){
            return true;
        }
        return false;
    }
}
