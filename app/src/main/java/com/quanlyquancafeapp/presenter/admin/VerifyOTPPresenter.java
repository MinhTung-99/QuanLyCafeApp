package com.quanlyquancafeapp.presenter.admin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.auth.AuthResult;
import com.quanlyquancafeapp.NewPasswordActivity;
import com.quanlyquancafeapp.RegisterActivity;
import com.quanlyquancafeapp.VerifyOTPActivity;
import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.utils.Constance;

public class VerifyOTPPresenter {
    private DatabaseHelper db;

    public VerifyOTPPresenter(Context context) {
        db = new DatabaseHelper(context);
    }

    public void handleIntent(Activity activity, AuthResult authResult){
        if(db.getUsers().size() == 0){
            Intent intent = new Intent(activity, RegisterActivity.class);
            intent.putExtra("phone_number", authResult.getUser().getPhoneNumber());
            activity.startActivityForResult(intent, Constance.REQUEST_CODE);
        }else {
            Intent intent = new Intent(activity, NewPasswordActivity.class);
            activity.startActivityForResult(intent, Constance.REQUEST_CODE);
        }
    }
}
