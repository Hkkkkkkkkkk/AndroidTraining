package com.example.androidtraining.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.example.androidtraining.Beans.ChatBeans;
import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.Beans.LoginBeans;
import com.example.androidtraining.Beans.UserBeans;
import com.example.androidtraining.DBHelper.NoteDAOService;
import com.example.androidtraining.DBHelper.NoteDAOServiceImpl;
import com.example.androidtraining.Dao.TypeDao;
import com.example.androidtraining.Dao.UserDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.sql.SQLException;
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

    public void  AsyncTaskBeans(final Activity activity, final Context context, final DataCallback dataCallback, final String md5user){

         @SuppressLint("StaticFieldLeak")
         AsyncTask<Void, Void, DataBeans> voidVoidDataBeansAsyncTask = new AsyncTask<Void, Void, DataBeans>() {
             Exception exception;
             @Override
             protected DataBeans doInBackground(Void... voids) {
                 try {
                     return ListDataBeans(activity,context,md5user);
                 } catch (IOException e) {
                     e.printStackTrace();
                     this.exception = e;
                 }
                 return null;
             }

             @Override
             protected void onPostExecute(DataBeans dataBeans) {
                 if (dataBeans == null || exception != null){
                     dataCallback.onFailed(exception);
                 }else{
                     dataCallback.onSuccess(dataBeans);
                 }
             }

         };
         voidVoidDataBeansAsyncTask.execute();
    }

    private DataBeans ListDataBeans(Activity activity,Context context,String md5user) throws IOException {
             DataBeans dataBeans = new DataBeans();
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
                dataDao(activity,context,dataBeans);
                return dataBeans;
            }
    }
    private List<TypeDao> dataDao(Activity activity,Context context,DataBeans dataBeans){
        List<TypeDao> typeDaoList = null;
        NoteDAOService<UserDao,Integer> service = new NoteDAOServiceImpl<>(context,UserDao.class);
        try {
            List<UserDao> userdata= service.Vague(1);
            List<UserDao> userDaos =service.queryAll();
            if (dataBeans.getUserBeans().size()!=userdata.size()) {
                for (UserDao userDao:userDaos){
                    service.delete(userDao);
                }
                for (UserBeans userBeans :dataBeans.getUserBeans()){
                    UserDao  userDao =new UserDao(userBeans.getName(),1);
                    service.create(userDao);
                }
                phoneData(activity,context,service);
            }



        }catch (SQLException e) {
            e.printStackTrace();
        }
        return typeDaoList;
    }
    private void phoneData (Activity activity,Context context, NoteDAOService<UserDao, Integer> service) throws SQLException {
        UserDao userDao;
        MangerPower.Power(activity);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        assert cursor != null;
        while(cursor.moveToNext()){
            String name= cursor.getString(cursor.getColumnIndex("display_name"));
            userDao = new UserDao(name,2);
            service.create(userDao);
        }
        cursor.close();
    }
    public interface DataCallback{
        void onSuccess(DataBeans dataBeans);
        void onFailed(Exception ex);
     }


}


