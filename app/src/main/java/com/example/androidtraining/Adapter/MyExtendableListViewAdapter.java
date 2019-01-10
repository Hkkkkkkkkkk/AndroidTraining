package com.example.androidtraining.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.androidtraining.Beans.UserBeans;
import com.example.androidtraining.Dao.TypeDao;
import com.example.androidtraining.R;

import java.util.List;

/**
 * Created by 黄铿 on 2019/1/10.
 */

public class MyExtendableListViewAdapter extends BaseExpandableListAdapter {
    private List<TypeDao> typeDaoList;
    private LayoutInflater inflater;

    public MyExtendableListViewAdapter(Context context, List<TypeDao> typeDaos){
        this.typeDaoList = typeDaos;
        this.inflater = LayoutInflater.from(context.getApplicationContext());

    }
    @Override
    public int getGroupCount() {
        return typeDaoList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return typeDaoList.get(groupPosition).getUserDaoList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return typeDaoList.get(groupPosition).getName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return typeDaoList.get(groupPosition).getUserDaoList().get(childPosition).getName();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.partent_item,parent,false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.label_group_normal);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder)convertView.getTag();
        }
        groupViewHolder.tvTitle.setText(typeDaoList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.fragment_second_zz,parent,false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.tvTitle = (TextView)convertView.findViewById(R.id.tv_name);
            convertView.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.tvTitle.setText(typeDaoList.get(groupPosition).getUserDaoList().get(childPosition).getName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    static class GroupViewHolder {
        TextView tvTitle;
    }

    static class ChildViewHolder {
        TextView tvTitle;

    }
}
