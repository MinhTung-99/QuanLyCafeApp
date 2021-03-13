package com.quanlyquancafeapp.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class BindingUitls {
    @BindingAdapter("imageDrawable")
    public static void loadImage(ImageView imageView, int drawable){
        imageView.setImageResource(drawable);
    }
    @BindingAdapter("imageBitmap")
    public static void loadImageBitmap(ImageView imageView, Bitmap bitmap){
        imageView.setImageBitmap(bitmap);
    }
}
