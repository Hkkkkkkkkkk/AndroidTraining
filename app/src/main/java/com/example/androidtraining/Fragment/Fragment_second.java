package com.example.androidtraining.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtraining.Adapter.UserAdapter;
import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.Beans.UserBeans;
import com.example.androidtraining.R;
import com.example.androidtraining.Util.AsyncTaskUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Fragment_second extends Fragment {
    @Bind(R.id.user_list) ListView mListView;
    private AsyncTaskUtil userAsyncTask;
    private UserAdapter userAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        ButterKnife.bind(this,view);
        userAsyncTask = new AsyncTaskUtil();
        userAsyncTask.AsyncTaskBeans(getContext(), new AsyncTaskUtil.DataCallback() {
            @Override
            public void onSuccess(DataBeans dataBeans) {
                userAdapter =new UserAdapter(getContext(),dataBeans.getUserBeans());
                mListView.setAdapter(userAdapter);
            }

            @Override
            public void onFailed(Exception ex) {

            }
        },"");

        return view;

    }


}


