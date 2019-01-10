package com.example.androidtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.Util.AsyncTaskUtil;
import com.example.androidtraining.Util.PdDisplayUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄铿 on 2019/1/7.
 */

public class RegisterActivity extends AppCompatActivity{
    @Bind(R.id.edit_username)    EditText edit_username;
    @Bind(R.id.edit_account)     EditText edit_account;
    @Bind(R.id.edit_password)    EditText edit_password;
    @Bind(R.id.Cfmpassword)      EditText cfmpassword;
    @Bind(R.id.et_showPassword)  ImageView et_showPassword;
    @Bind(R.id.cfm_showPassword) ImageView cfm_showPassword;
    private Boolean showPassword = true;
    private Boolean cfmshowPassword = true;
    private AsyncTaskUtil registerAsyncTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.register,R.id.comeback_login,R.id.cfm_showPassword,R.id.et_showPassword})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.register:
                register();
                break;
            case R.id.comeback_login:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;
            case  R.id.cfm_showPassword:
                PdDisplayUtil.passwordDisplay(cfmshowPassword,cfmpassword,cfm_showPassword);
                cfmshowPassword = !cfmshowPassword;
                break;
            case R.id.et_showPassword:
                PdDisplayUtil.passwordDisplay(showPassword,edit_password,et_showPassword);
                showPassword = !showPassword;
                break;
        }

    }
    private void register(){
        final String account = edit_account.getText().toString();
        String username = edit_username.getText().toString();
        String password = edit_password.getText().toString();
        String password2 = cfmpassword.getText().toString();
        if (username.equals("")||account.equals("")||password.equals("")) {

            Toast.makeText(this,"请确认信息是否填写完整！",Toast.LENGTH_LONG).show();
        }else if (!password.equals(password2)){
            Toast.makeText(this,"请确认两次密码是否输入相同！",Toast.LENGTH_LONG).show();

        }else {
            registerAsyncTask = new AsyncTaskUtil(username,account,password);
            registerAsyncTask.AsyncTaskBeans(this,this, new AsyncTaskUtil.DataCallback() {
                @Override
                public void onSuccess(DataBeans dataBeans) {
                    String status = dataBeans.getStatus();
                    if (status.equals("注册成功")){
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        intent.putExtra("user",account);
                        startActivity(intent);
                        Toast.makeText(RegisterActivity.this,status,Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,status,Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed(Exception ex) {

                }

            },"");
        }
    }
}
