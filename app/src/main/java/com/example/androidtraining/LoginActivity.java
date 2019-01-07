package com.example.androidtraining;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.Beans.LoginBeans;
import com.example.androidtraining.Util.AsyncTaskUtil;
import com.example.androidtraining.Util.DataUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄铿 on 2019/1/7.
 */

public class LoginActivity extends AppCompatActivity {
    @Bind(R.id.login_account)  EditText login_account;
    @Bind(R.id.login_password) EditText login_password;
    private AsyncTaskUtil loginAsyncTask;
    private DataUtil dataUtil = new DataUtil();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        ButterKnife.bind(this);
        InitializationData();
    }
    private void InitializationData(){
        String user;
        user = getIntent().getStringExtra("user");
        if (user==null){
            SharedPreferences sharedPreferences = getSharedPreferences(DataUtil.loginDataName,MODE_PRIVATE);
            user = sharedPreferences.getString(DataUtil.Usre,"");
            String password =sharedPreferences.getString(DataUtil.Password,"");
            login_account.setText(user);
            login_password.setText(password);
        }else {
            login_account.setText(user);
        }
    }
    @OnClick({R.id.comeback_register,R.id.login})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.login:
                Login();
                break;
            case R.id.comeback_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }
    private void Login(){
        final String account  = login_account.getText().toString().trim();
        final String password = login_password.getText().toString().trim();
        if (account.equals("")&&password.equals("")){
            Toast.makeText(this,"帐号或密码不能为空哦！",Toast.LENGTH_LONG).show();
        }else {
            loginAsyncTask = new AsyncTaskUtil(account,password);
            loginAsyncTask.AsyncTaskBeans(this, new AsyncTaskUtil.DataCallback() {
                @Override
                public void onSuccess(DataBeans dataBeans) {
                    LoginBeans loginBeans = dataBeans.getLoginBeans();
                    String status = loginBeans.getStatus();
                    if (status.equals("登陆成功")){
                        dataUtil.LocalStorage(LoginActivity.this,account,password);
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("user",loginBeans.getUser());
                        intent.putExtra("name",loginBeans.getName());
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this,"帐号或密码有误，请重新输入！",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void onFailed(Exception ex) {

                }
            },"");
        }

    }

}

