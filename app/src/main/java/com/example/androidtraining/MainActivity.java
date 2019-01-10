package com.example.androidtraining;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androidtraining.DBHelper.NoteDAOService;
import com.example.androidtraining.DBHelper.NoteDAOServiceImpl;
import com.example.androidtraining.Dao.TypeDao;
import com.example.androidtraining.Dao.UserDao;
import com.example.androidtraining.Fragment.Fragment_first;
import com.example.androidtraining.Fragment.Fragment_second;
import com.example.androidtraining.Fragment.Fragment_third;

import java.lang.reflect.Type;
import java.sql.SQLException;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "sss";
    private RelativeLayout firstlayout,secondlayout,thirdlayout;
    protected Fragment_first fragment_first = new Fragment_first();
    protected Fragment_second fragment_second = new Fragment_second();
    protected Fragment_third fragment_third = new Fragment_third();
    private ImageView first_image,second_image,third_image;
    private TextView first_text,second_text,third_text;
    private String name;
    private String user;
    private String[] userdata;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        initView();
        setView();
        initTransmit();
        setListent();
    }
    public String[] getName() {
        return userdata;
    }
    private void initTransmit() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        user = intent.getStringExtra("user");
        userdata = new String[]{name,user};

    }

    private void setView() {
        this.getSupportFragmentManager()                                     // 获取Fragment对象
                .beginTransaction()                                          // 开启一个事物
                .add(R.id.fragment_buju,fragment_second)
                .add(R.id.fragment_buju,fragment_third)
                .add(R.id.fragment_buju,fragment_first)
                .show(fragment_second)
                .hide(fragment_first)
                .hide(fragment_third)
                .commit();

        first_text.setTextColor(getResources().getColor(R.color.gray));
        second_text.setTextColor(getResources().getColor(R.color.green));
        third_text.setTextColor(getResources().getColor(R.color.gray));
        first_image.setImageResource(R.drawable.chat_one);
        second_image.setImageResource(R.drawable.contacts_two);
        third_image.setImageResource(R.drawable.me_one);
    }

    private void setListent() {
        RelativeLayoutListent relativeLayout = new RelativeLayoutListent();
        firstlayout.setOnClickListener(relativeLayout);
        secondlayout.setOnClickListener(relativeLayout);
        thirdlayout.setOnClickListener(relativeLayout);
    }

    class RelativeLayoutListent implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.first_layout:
                    MainActivity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .show(fragment_first)
                            .hide(fragment_second)
                            .hide(fragment_third)
                            .commit();
                    first_text.setTextColor(getResources().getColor(R.color.green));
                    second_text.setTextColor(getResources().getColor(R.color.gray));
                    third_text.setTextColor(getResources().getColor(R.color.gray));
                    first_image.setImageResource(R.drawable.chat_two);
                    second_image.setImageResource(R.drawable.contacts_one);
                    third_image.setImageResource(R.drawable.me_one);
                    break;
                case R.id.second_layout:
                    MainActivity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .show(fragment_second)
                            .hide(fragment_first)
                            .hide(fragment_third)
                            .commit();
                    first_text.setTextColor(getResources().getColor(R.color.gray));
                    second_text.setTextColor(getResources().getColor(R.color.green));
                    third_text.setTextColor(getResources().getColor(R.color.gray));
                    first_image.setImageResource(R.drawable.chat_one);
                    second_image.setImageResource(R.drawable.contacts_two);
                    third_image.setImageResource(R.drawable.me_one);
                    break;
                case R.id.third_layout:
                    MainActivity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .show(fragment_third)
                            .hide(fragment_first)
                            .hide(fragment_second)

                            .commit();
                    first_text.setTextColor(getResources().getColor(R.color.gray));
                    second_text.setTextColor(getResources().getColor(R.color.gray));
                    third_text.setTextColor(getResources().getColor(R.color.green));
                    first_image.setImageResource(R.drawable.chat_one);
                    second_image.setImageResource(R.drawable.contacts_one);
                    third_image.setImageResource(R.drawable.me_two);
                    break;
            }
        }
    }

    private void initView() {
        firstlayout = findViewById(R.id.first_layout);
        secondlayout = findViewById(R.id.second_layout);
        thirdlayout = findViewById(R.id.third_layout);
        first_text = findViewById(R.id.first_text);
        second_text = findViewById(R.id.second_text);
        third_text = findViewById(R.id.third_text);
        first_image = findViewById(R.id.first_image);
        second_image = findViewById(R.id.second_image);
        third_image = findViewById(R.id.third_image);
    }

}
