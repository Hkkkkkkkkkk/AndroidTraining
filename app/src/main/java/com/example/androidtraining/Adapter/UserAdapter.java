package com.example.androidtraining.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtraining.Beans.UserBeans;
import com.example.androidtraining.R;

import java.util.List;

/**
 * Created by 黄铿 on 2019/1/8.
 */

public class UserAdapter extends BaseAdapter{
    private List<UserBeans> userBeansList;
    private LayoutInflater inflater;
    public UserAdapter(Context context,List<UserBeans> userBeans){
        this.userBeansList = userBeans;
        this.inflater = LayoutInflater.from(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return userBeansList.size();
    }

    @Override
    public Object getItem(int position) {
        return userBeansList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView==null){
            viewHolder =new ViewHolder();
            convertView = inflater.inflate(R.layout.fragment_second_zz,parent,false);
            viewHolder.textView = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        }else {
           viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(userBeansList.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
