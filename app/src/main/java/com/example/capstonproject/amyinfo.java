package com.example.capstonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationBarView;

public class amyinfo extends AppCompatActivity {
    TextView tv_name, tv_id, tv_sex, tv_tel, tv_major;
    Button reload_myinfo, btn_backtohome;
    SharedPreferences USERINFO = getSharedPreferences("USERINFO",MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amyinfo);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_tel = (TextView) findViewById(R.id.tv_tel);
        tv_major = (TextView) findViewById(R.id.tv_major);

        tv_name.setText(USERINFO.getString("name", "이름없음"));
        tv_id.setText(USERINFO.getString("id", "id없음"));
        tv_sex.setText(USERINFO.getString("sex", "성별없음"));
        tv_tel.setText(USERINFO.getString("phone", "폰 번호 없음"));
        tv_major.setText(USERINFO.getString("major", "학과없음"));


        reload_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        btn_backtohome.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }

}