package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class cmatching_detail extends AppCompatActivity {
    TextView creater_id, match_title;


    private Intent intent;
    String match_number, result;
    String[] matchInformation;
    String str_creater_id, str_match_title, str_exercise_type, str_match_type, str_match_time, str_recruit_person, str_match_sex, str_match_major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmatching_detail);

        intent = getIntent();
        match_number = intent.getStringExtra("match_number");
        Log.e("custom", ""+match_number);
//
        try {
            yTask request = new yTask("matchInformation");
            result = request.execute(match_number).get();

            matchInformation = result.split(":")[1].split(",");
            str_creater_id = matchInformation[0];
            str_match_title = matchInformation[1];
            str_exercise_type = matchInformation[2];
            str_match_type = matchInformation[3];
            str_match_time = matchInformation[4];
            str_recruit_person = matchInformation[5];
            str_match_sex = matchInformation[6];
            str_match_major = matchInformation[7];

            Log.e("detail-customlog", "불러오기 성공");




        }catch (Exception e){
            Log.e("detail-customlog", e.getMessage());
        }



    }
}