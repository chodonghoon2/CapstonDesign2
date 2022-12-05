package com.example.capstonproject;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class y_members_adapter extends BaseAdapter {
    private ArrayList<ymatch_members> members_list = new ArrayList<ymatch_members>();

    public y_members_adapter(){}

    @Override
    public int getCount() {
        return members_list.size();
    }

    public Object getItem(int position){
        return members_list.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View view, ViewGroup parent){
        Context ct = parent.getContext();

        //아이템 레이아웃 생성
        LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.ymatch_members_item, parent, false);



        return view;
    }
}
