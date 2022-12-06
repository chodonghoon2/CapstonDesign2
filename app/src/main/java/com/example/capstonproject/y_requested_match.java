package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class y_requested_match extends AppCompatActivity {

    ListView request_list;
    TextView user_name, user_major;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yrequested_match);

        SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);
        String creater_id = USERINFO.getString("id", "id없음");
        String str_user_name = USERINFO.getString("name", "이름없음");
        String str_user_major = USERINFO.getString("major", "학과없음");

        user_name = (TextView) findViewById(R.id.user_name);
        user_major = (TextView) findViewById(R.id.user_major);
        user_name.setText(str_user_name);
        user_major.setText(str_user_major);


        request_list = findViewById(R.id.request_list);
        y_members_adapter adapter = new y_members_adapter();
        request_list.setAdapter(adapter);
        String[] creat_matchs;
        try{
            yTask authorityTask = new yTask("authority");
            String result_matchs = authorityTask.execute("&a=1&creater_id=" + creater_id).get();
            creat_matchs = result_matchs.split("/");
            // match_number, user_id, user_name, user_major, user_phone

            for(String requested_matchs : creat_matchs){
                String[] mInfo = requested_matchs.split(",");
                Log.e("additem-log", mInfo[0] + mInfo[1] + mInfo[2] + mInfo[3] + mInfo[4]);
                adapter.addItem(mInfo[0], mInfo[1], mInfo[2], mInfo[3], mInfo[4]);
            }


        }catch(Exception e){

        }





    }
}