package com.example.androidtraining.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import com.example.androidtraining.Beans.ChatBeans;
import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.Beans.LoginBeans;
import com.example.androidtraining.Beans.UserBeans;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

/**
 * Created by 黄铿 on 2019/1/6.
 */

public class AsyncTaskUtil {
    private String name;
    private String user;
    private String password;
    private String chat;

    public AsyncTaskUtil(String name, String user, String password) {
        this.name = name;
        this.user = user;
        this.password = password;
    }

    public AsyncTaskUtil(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public AsyncTaskUtil(String chat) {
        this.chat = chat;
    }

    public AsyncTaskUtil() {
    }

    public void  AsyncTaskBeans(final Context context, final DataCallback dataCallback, final String md5user){
         @SuppressLint("StaticFieldLeak")
         AsyncTask<Void, Void, DataBeans> voidVoidDataBeansAsyncTask = new AsyncTask<Void, Void, DataBeans>() {
             @Override
             protected DataBeans doInBackground(Void... voids) {

                 return ListDataBeans(context,md5user);
             }

             @Override
             protected void onPostExecute(DataBeans dataBeans) {
                 dataCallback.onSuccess(dataBeans);
             }

         };
         voidVoidDataBeansAsyncTask.execute();
    }

    private DataBeans ListDataBeans(Context context,String md5user){
        try {
             DataBeans dataBeans =new DataBeans();
            if (name!=null&&user!=null&&password!=null){
                dataBeans = new Gson().fromJson(HttpUtil.registerData(context, name, user, password), DataBeans.class);
                return dataBeans;
            }else if (user!=null&&password!=null){
                LoginBeans loginBeans = new Gson().fromJson(HttpUtil.loginData(context, user, password), LoginBeans.class);
                dataBeans.setLoginBeans(loginBeans);
                return dataBeans;
            }else if (chat!=null){
                List<ChatBeans> chatBeansList = new Gson().fromJson(HttpUtil.chatData(context, md5user, chat), new TypeToken<List<ChatBeans>>() {}.getType());
                dataBeans.setChatBeans(chatBeansList);
                return dataBeans;
            }else {
                List<UserBeans> userBeansList = new Gson().fromJson(HttpUtil.userData(context),new TypeToken<List<UserBeans>>(){}.getType());
                dataBeans.setUserBeans(userBeansList);
                return dataBeans;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
     interface DataCallback{
        void onSuccess(DataBeans dataBeans);
        void onFailed();
    }
}


