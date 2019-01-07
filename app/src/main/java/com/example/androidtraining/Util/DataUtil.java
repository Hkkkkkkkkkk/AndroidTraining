package com.example.androidtraining.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 黄铿 on 2019/1/7.
 */

public class DataUtil {
    public static final String loginDataName = "loginData";
    public static final String Usre = "user";
    public static final String Password = "password";


    public void LocalStorage(Context context,String user,String password){
        SharedPreferences userSettings = context.getSharedPreferences(loginDataName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=userSettings.edit();
        editor.putString(Usre,user);
        editor.putString(Password,password);
        editor.apply();
    }
}
