package com.example.androidtraining.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtraining.R;



public class Fragment_second extends Fragment {
    private ListView mListView;
    private String [] names ={"a","b","c","d","e","f","g","h","i","j"};
    private int [] icons = {R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second,container,false);
        mListView = view.findViewById(R.id.list);
        MyBaseAdapter myBaseAdapter = new MyBaseAdapter();
        mListView.setAdapter(myBaseAdapter);
        return view;
    }
    class MyBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(getActivity(),R.layout.fragment_second_zz,null);
            TextView textView = view.findViewById(R.id.tv_name);
            ImageView imageView = view.findViewById(R.id.iv_icon);
            textView.setText(names[position]);
            imageView.setImageResource(icons[position]);
            return view;
        }
    }
}


