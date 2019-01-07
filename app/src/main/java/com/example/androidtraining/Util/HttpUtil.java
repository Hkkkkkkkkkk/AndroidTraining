package com.example.androidtraining.Util;


import android.content.Context;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    private static final String registerurl = "http://123.207.85.214/chat/register.php";
    private static final String loginurl = "http://123.207.85.214/chat/login.php";
    private static final String chaturl = "http://123.207.85.214/chat/chat1.php";
    private static final String userurl = "http://123.207.85.214/chat/member.php";
    public static String registerData(Context context,String name, String user, String password) throws IOException {
        OkHttpClient client =new OkHttpClient();
        RequestBody requestBody =new FormBody.Builder()
                .add("name",name)
                .add("user",user)
                .add("password",password)
                .build();
        Request request =new Request.Builder()
                .url(registerurl)
                .post(requestBody)
                .build();
        Response response =client.newCall(request).execute();
        if (response!=null){
            return response.body().string();
        }else {
            Toast.makeText( context,"网络异常！",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public static String loginData(Context context, String user, String password) throws IOException {
        OkHttpClient client =new OkHttpClient();
        RequestBody requestBody =new FormBody.Builder()
                .add("user",user)
                .add("password",password)
                .build();
        Request request =new Request.Builder()
                .url(loginurl)
                .post(requestBody)
                .build();
        Response response =client.newCall(request).execute();
        if (response!=null){
            return response.body().string();
        }else {
            Toast.makeText( context,"网络异常！",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public static String chatData(Context context, String user, String chat) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("user",user)
                .add("chat",chat)
                .build();
        Request request = new Request.Builder()
                .url(chaturl)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response!=null){
            return response.body().string();
        }else {
            Toast.makeText( context,"网络异常！",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public static String userData(Context context) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(userurl)
                .build();
        Response response = client.newCall(request).execute();
        if (response!=null){
            return response.body().string();
        }else {
            Toast.makeText( context,"网络异常！",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
