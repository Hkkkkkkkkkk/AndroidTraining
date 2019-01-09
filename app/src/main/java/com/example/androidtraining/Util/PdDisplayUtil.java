package com.example.androidtraining.Util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.androidtraining.R;

/**
 * Created by 黄铿 on 2019/1/8.
 */

public class PdDisplayUtil {
    public static void passwordDisplay(Boolean showPassword,EditText editText, ImageView imageView){
        if (showPassword) {// 显示密码
            imageView.setBackgroundResource(R.drawable.eye_o);
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            editText.setSelection(editText.getText().toString().length());

        } else {// 隐藏密码
            imageView.setBackgroundResource(R.drawable.eye_c);
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            editText.setSelection(editText.getText().toString().length());

        }

    }


}
