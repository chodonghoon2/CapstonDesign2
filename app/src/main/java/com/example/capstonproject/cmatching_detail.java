package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class cmatching_detail extends AppCompatActivity {
    TextView textview_match_number;


    private Intent intent;
    String match_number, result;
    String[] matchInformation;
    String creater_id, match_title, exercise_type, match_type, match_time, recruit_person, match_sex, match_major;

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
            creater_id = matchInformation[0];
            match_title = matchInformation[1];
            exercise_type = matchInformation[2];
            match_type = matchInformation[3];
            match_time = matchInformation[4];
            recruit_person = matchInformation[5];
            match_sex = matchInformation[6];
            match_major = matchInformation[7];

            Log.e("detail-customlog", "불러오기 성공");

        }catch (Exception e){
            Log.e("detail-customlog", e.getMessage());
        }



    }
}