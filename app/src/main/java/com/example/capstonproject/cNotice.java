package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class cNotice extends AppCompatActivity {

    ListView notice_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnotice);
        SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);

        notice_listview = (ListView) findViewById(R.id.notice_listview);
        y_notice_adapter adapter = new y_notice_adapter();
        notice_listview.setAdapter(adapter);

        String user_id = USERINFO.getString("id", "id없음");
        String[] nList = new NoticeObj(user_id).call_notice();

        for(String noticeItem : nList){
            String[] noticeInfo = noticeItem.split(",");
            //send_id, message, send_time
            adapter.addItem(noticeInfo[1], noticeInfo[0], noticeInfo[2]);
        }

    }
}