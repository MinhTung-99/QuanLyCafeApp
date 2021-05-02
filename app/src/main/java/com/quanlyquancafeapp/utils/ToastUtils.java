package com.quanlyquancafeapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.quanlyquancafeapp.R;

public class ToastUtils {
    public static void showToast(Activity activity, String msg){
        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.TOP, 0 , 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        View view = activity.getLayoutInflater().inflate(R.layout.custom_toast, null);
        TextView txtToast = view.findViewById(R.id.txt_toast);
        txtToast.setText(msg);
        toast.setView(view);
        toast.show();
    }
}
