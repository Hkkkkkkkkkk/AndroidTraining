package com.example.androidtraining.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtraining.Adapter.ChatAdapter;
import com.example.androidtraining.Beans.ChatBeans;
import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.MainActivity;
import com.example.androidtraining.R;
import com.example.androidtraining.Util.AsyncTaskUtil;

import java.util.List;

import javax.security.auth.callback.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Fragment_first extends Fragment {
    @Bind(R.id.chat_list) ListView listView;
    @Bind(R.id.ed_chat)   EditText ed_chat;
    @Bind(R.id.btn_sub)   Button btn_sub;
    private String[] chatdata;
    private AsyncTaskUtil chatAsyncTaskUtil;
    private ChatAdapter chatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        ButterKnife.bind(this,view);
        return view;

    }

    @OnClick ({R.id.btn_sub})
    public void onViewClicked(View view){
        switch (view.getId()){
            case R.id.btn_sub:
                chatAsyncTaskUtil = new AsyncTaskUtil(ed_chat.getText().toString());
                chatAsyncTaskUtil.AsyncTaskBeans(getContext(), new AsyncTaskUtil.DataCallback() {
                    @Override
                    public void onSuccess(DataBeans dataBeans) {
                        chatAdapter = new ChatAdapter(getContext(),dataBeans.getChatBeans(),chatdata[0]);
                        chatAdapter.CheckData(dataBeans.getChatBeans());
                        listView.setAdapter(chatAdapter);

                    }

                    @Override
                    public void onFailed(Exception ex) {

                    }
                },chatdata[1]);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        chatdata = ((MainActivity) context).getName();
    }
}
