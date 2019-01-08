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

import com.example.androidtraining.MainActivity;
import com.example.androidtraining.R;


public class Fragment_first extends Fragment {
    private ListView listView;
    private EditText ed_chat;
    private Button btn_sub;
    private String [] names ={"a","b","c","d","e","f","g","h","i","j"};
    private int [] icons = {R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one,R.drawable.me_one};
    private String[] a;
    private String name;
    private String inputText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first,container,false);
        initView(view);
        MBaseAdapter mBaseAdapter = new MBaseAdapter();
        listView.setAdapter(mBaseAdapter);
        return view;

    }

    private void initView(View view) {
        listView = view.findViewById(R.id.list);
        ed_chat = view.findViewById(R.id.ed_chat);
        btn_sub = view.findViewById(R.id.btn_sub);
        btn_sub.setOnClickListener(new ButtonListent());
    }

    class ButtonListent implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            inputText = ed_chat.getText().toString();
        }
    }
    class MBaseAdapter extends BaseAdapter{

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
            ViewHolder holder;
            if(==name){
                if (convertView ==null) {
                    convertView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_xx,parent,false);
                    holder = new ViewHolder();
                    holder.textView = convertView.findViewById(R.id.tv_name);
                    holder.imageView = convertView.findViewById(R.id.iv_icon);
                    holder.textView1 = convertView.findViewById(R.id.tv_chat);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                    holder.imageView.setImageResource(icons[position]);
                    holder.textView.setText(names[position]);
                    holder.textView1.setText(inputText);
                return convertView;
            }else{
                if (convertView ==null) {
                    convertView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_first_zz, parent, false);
                    holder = new ViewHolder();
                    holder.textView = convertView.findViewById(R.id.tv_name);
                    holder.imageView = convertView.findViewById(R.id.iv_icon);
                    holder.textView1 = convertView.findViewById(R.id.tv_chat);
                }else{
                    holder = (ViewHolder) convertView.getTag();
                }
                holder.imageView.setImageResource(icons[position]);
                holder.textView.setText(names[position]);
                holder.textView1.setText("");

                return convertView;
            }

            return null;
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        a = ((MainActivity) context).getName();
        name = a[0];
    }
    class ViewHolder{
        TextView textView,textView1;
        ImageView imageView;
    }
}
