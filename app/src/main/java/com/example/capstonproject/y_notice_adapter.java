package com.example.capstonproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class y_notice_adapter extends BaseAdapter {
    private ArrayList<y_notice_item> notice_list = new ArrayList<y_notice_item>();

    public y_notice_adapter(){}

    @Override
    public int getCount() {
        return notice_list.size();
    }

    public Object getItem(int position){
        return notice_list.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View view, ViewGroup parent){
        Context ct = parent.getContext();

        //아이템 레이아웃 생성
        LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.y_notice_layout, parent, false);

        TextView notice_msg = view.findViewById(R.id.notice_msg);
        TextView send_id = view.findViewById(R.id.send_id);
        TextView send_time = view.findViewById(R.id.send_time);

        y_notice_item notice_item = notice_list.get(position);

        notice_msg.setText(notice_item.getMsg());
        send_id.setText(notice_item.getSend_id());
        send_time.setText(notice_item.getSend_time());

        return view;
    }

    public void addItem(String msg, String send_id, String send_time){
        y_notice_item notice_item = new y_notice_item(msg, send_id, send_time);
        notice_list.add(notice_item);
    }

}
