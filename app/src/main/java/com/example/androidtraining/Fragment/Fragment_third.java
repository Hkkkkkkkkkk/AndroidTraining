package com.example.androidtraining.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidtraining.MainActivity;
import com.example.androidtraining.R;



public class Fragment_third extends Fragment {
    private TextView tv_name;
    private String [] a ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third,container,false);
        initview(view);
        tv_name.setText(a[0]);
        return view;
    }

    private void initview(View view) {
        tv_name = view.findViewById(R.id.tv_name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        a = ((MainActivity) context).getName();
    }
}
