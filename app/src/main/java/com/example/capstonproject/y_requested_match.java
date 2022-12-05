package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

public class y_requested_match extends AppCompatActivity {

    ListView request_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yrequested_match);

        SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);
        String creater_id = USERINFO.getString("id", "id없음");

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
                adapter.addItem(mInfo[0], mInfo[1], mInfo[2], mInfo[3], mInfo[4]);
            }


        }catch(Exception e){

        }





    }
}