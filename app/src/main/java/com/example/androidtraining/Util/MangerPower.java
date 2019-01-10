package com.example.androidtraining.Util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public class MangerPower {
    private final static int PowerInt = 1;
    private final static String[] PowerCheck = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//读45
            Manifest.permission.READ_EXTERNAL_STORAGE,
//通讯录
            Manifest.permission.READ_CONTACTS,
//通话记录
            Manifest.permission.READ_CALL_LOG,

    };
    public static void Power(Activity activity){
        int permission = ActivityCompat.checkSelfPermission(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity,PowerCheck,PowerInt);
        }
    }
}
