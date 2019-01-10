package com.example.androidtraining.Fragment;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtraining.Adapter.MyExtendableListViewAdapter;
import com.example.androidtraining.Adapter.UserAdapter;
import com.example.androidtraining.Beans.DataBeans;
import com.example.androidtraining.Beans.UserBeans;
import com.example.androidtraining.DBHelper.NoteDAOService;
import com.example.androidtraining.DBHelper.NoteDAOServiceImpl;
import com.example.androidtraining.Dao.TypeDao;
import com.example.androidtraining.Dao.UserDao;
import com.example.androidtraining.MainActivity;
import com.example.androidtraining.R;
import com.example.androidtraining.Util.AsyncTaskUtil;
import com.example.androidtraining.Util.MangerPower;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Fragment_second extends Fragment {
    @Bind(R.id.ex_list) ExpandableListView expandableListView;
    private AsyncTaskUtil userAsyncTask;
    private MyExtendableListViewAdapter ViewAdapter;
    private List<UserBeans> userBeansList = new ArrayList<>();
    private UserDao userDao;
    private TypeDao chatDao;
    private TypeDao phoneDao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        ButterKnife.bind(this,view);
        userAsyncTask = new AsyncTaskUtil();
        userAsyncTask.AsyncTaskBeans(getActivity(),getContext(), new AsyncTaskUtil.DataCallback() {
            @Override
            public void onSuccess(DataBeans dataBeans) {
                try {
                    NoteDAOService<UserDao,Integer> service = new NoteDAOServiceImpl<>(getContext(),UserDao.class);
                    List<TypeDao> typeDaoList =new ArrayList<>();
                    chatDao = new TypeDao(1,"群聊联系人");
                    chatDao.setUserDaoList(service.Vague(1));
                    typeDaoList.add(chatDao);
                    phoneDao =new TypeDao(2,"手机联系人");
                    phoneDao.setUserDaoList(service.Vague(2));
                    typeDaoList.add(phoneDao);
                    ViewAdapter =new MyExtendableListViewAdapter(getContext(),typeDaoList);
                    expandableListView.setAdapter(ViewAdapter);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(Exception ex) {
            }
        },"");
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                return false;
            }
        });
        return view;

    }


}


