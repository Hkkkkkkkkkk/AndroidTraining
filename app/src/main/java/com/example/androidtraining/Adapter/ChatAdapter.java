package com.example.androidtraining.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtraining.Beans.ChatBeans;
import com.example.androidtraining.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄铿 on 2019/1/9.
 */

public class ChatAdapter extends BaseAdapter{
    private List<ChatBeans> chatBeansList;
    private String name;
    private Context contextA;
    private LayoutInflater inflater;
    public ChatAdapter(Context context,List<ChatBeans> chatBeansList,String name) {
        this.chatBeansList = chatBeansList;
        this.name = name;
        this.contextA = context;
        this.inflater = LayoutInflater.from(context.getApplicationContext());
    }
    public void CheckData(List<ChatBeans> chatBeans){
        this.chatBeansList = chatBeans;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return chatBeansList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatBeansList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(name.equals(chatBeansList.get(position).getName()) ? R.layout.fragment_first_right : R.layout.fragment_first_left, parent, false);
            viewHolder =new ViewHolder(convertView);

        }else if(!name.equals(chatBeansList.get(position).getName())){
            convertView = inflater.inflate(name.equals(chatBeansList.get(position).getName()) ? R.layout.fragment_first_right : R.layout.fragment_first_left, parent, false);
            viewHolder =new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.chat.setMaxWidth(700);
        viewHolder.chat.setMaxHeight(1000);
        viewHolder.chat.setText(chatBeansList.get(position).getChat());
        viewHolder.name.setText(chatBeansList.get(position).getName());
        return convertView;
    }
     class ViewHolder{
        TextView chat;
        TextView name;
         public ViewHolder(View convertView) {
             chat = convertView.findViewById(R.id.chat);
             name = convertView.findViewById(R.id.name);
             //可能为空
             convertView.setTag(this);
         }

     }

}
