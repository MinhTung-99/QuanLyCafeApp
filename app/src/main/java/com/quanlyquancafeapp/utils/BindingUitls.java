package com.quanlyquancafeapp.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingUitls {
    @BindingAdapter("imageDrawable")
    public static void loadImage(ImageView imageView, int drawable){
        imageView.setImageResource(drawable);
    }
}
