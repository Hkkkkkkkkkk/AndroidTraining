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
        userAsyncTask.AsyncTaskBeans(getContext(), new AsyncTaskUtil.DataCallback() {
            @Override
            public void onSuccess(DataBeans dataBeans) {
                ViewAdapter =new MyExtendableListViewAdapter(getContext(),dataDao(dataBeans));
                expandableListView.setAdapter(ViewAdapter);
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
    private List<TypeDao> dataDao(DataBeans dataBeans){
        List<TypeDao> typeDaoList = null;
         NoteDAOService<UserDao,Integer> service = new NoteDAOServiceImpl<>(getContext(),UserDao.class);
        try {
            List<UserDao> userDaos = service.queryAll();
            if (dataBeans.getUserBeans().size()!=userDaos.size()) {
                for (UserDao userDao:userDaos){
                    service.delete(userDao);
                }
                for (UserBeans userBeans :dataBeans.getUserBeans()){
                    userDao =new UserDao();
                    userDao.setName(userBeans.getName());
                    userDao.setTid(1);
                    service.create(userDao);
                }

            }
            phoneData(service);
            typeDaoList =new ArrayList<>();
            chatDao = new TypeDao(1,"群聊联系人");
            chatDao.setUserDaoList(service.Vague(1));
            typeDaoList.add(chatDao);
            phoneDao =new TypeDao(2,"手机联系人");
            phoneDao.setUserDaoList(service.Vague(2));
            typeDaoList.add(phoneDao);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return typeDaoList;
    }
    private void phoneData ( NoteDAOService<UserDao,Integer> service) throws SQLException {
        UserDao userDao;
        MangerPower.Power(getActivity());
        ContentResolver contentResolver = getContext().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);
        assert cursor != null;
        while(cursor.moveToNext()){
            String name= cursor.getString(cursor.getColumnIndex("display_name"));
            userDao = new UserDao();
            userDao.setName(name);
            userDao.setTid(2);
            service.create(userDao);
        }
        cursor.close();
    }

}


