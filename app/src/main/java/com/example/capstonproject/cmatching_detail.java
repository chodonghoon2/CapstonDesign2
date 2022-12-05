package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class cmatching_detail extends AppCompatActivity {
    TextView creater_id, match_title, match_persons, match_month, match_day, match_hour, match_minute;
    ImageView match_type, match_sex, exercise_type, match_major;
    Button apply_btn;

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

        creater_id = (TextView)findViewById(R.id.creater_id);
        match_title = (TextView) findViewById(R.id.match_title);
        match_type = (ImageView) findViewById(R.id.match_type);
        match_sex = (ImageView) findViewById(R.id.match_sex);
        exercise_type = (ImageView) findViewById(R.id.exercise_type);
        match_major = (ImageView) findViewById(R.id.match_major);
        match_persons = (TextView)findViewById(R.id.match_people);
        match_month = (TextView) findViewById(R.id.time_month);
        match_day = (TextView) findViewById(R.id.time_day);
        match_hour = (TextView) findViewById(R.id.time_hour);
        match_minute = (TextView) findViewById(R.id.time_minute);
        apply_btn = (Button) findViewById(R.id.apply_btn);

        try {
            yTask request = new yTask("matchInformation");
            result = request.execute("&a=1&match_number="+match_number).get();

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


            creater_id.setText(str_creater_id);
            match_title.setText(str_match_title);

            if(str_match_type.equals("용병")){
                match_type.setImageResource(R.drawable.merecenary);
            }else{
                match_type.setImageResource(R.drawable.rivals);
            }

            if(str_match_sex.equals("female")){
                match_sex.setImageResource(R.drawable.female);
            }else{
                match_sex.setImageResource(R.drawable.male);
            }

            switch(str_exercise_type){
                case "축구":
                    exercise_type.setImageResource(R.drawable.soccer);
                    break;
                case "풋살":
                    exercise_type.setImageResource(R.drawable.footsal);
                    break;
            }

            switch (str_match_major){
                case "컴퓨터터공학과":
                    match_major.setImageResource(R.drawable.major_coumputer);
                    break;
                case "영어영문학과":
                    match_major.setImageResource(R.drawable.major_english);
                    break;
                case "유아교육과":
                    match_major.setImageResource(R.drawable.major_child);
                    break;
                case "신학과":
                    match_major.setImageResource(R.drawable.major_christian);
                    break;
                case "경영학과":
                    match_major.setImageResource(R.drawable.major_economy);
                    break;
                case "약학과":
                    match_major.setImageResource(R.drawable.major_medicine);
                    break;
                case "간호학과":
                    match_major.setImageResource(R.drawable.major_nurse);
                    break;
                case "물리치료학과":
                    match_major.setImageResource(R.drawable.major_phsical);
                    break;
           }

            match_persons.setText(str_recruit_person);

            match_month.setText(str_match_time.substring(0, 2));
            match_day.setText(str_match_time.substring(2, 4));
            match_hour.setText(str_match_time.substring(4, 6));
            match_minute.setText(str_match_time.substring(6));




        }catch (Exception e){
            Log.e("detail-customlog", e.getMessage());
        }


        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);
                String user_id = USERINFO.getString("id", "");
                try{
                    yTask apply_request = new yTask("match_apply");
                    String result = apply_request.execute("&a=1&match_number=" + match_number + "&user_id=" + user_id).get();
                    if(result.equals("삽입성공")){
                        Log.e("detailpagelog", "삽입성공");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Log.e("detailpagelog", "삽입실패");

                    }
                }catch (Exception e){
                    Log.e("detailpagelog", e.getMessage());
                }
            }
        });



//        try{
//            yTask member_request = new yTask("members");
//            String result = member_request.execute("&a=1&match_number=" + match_number).get();
//            String[] members = result.split("/");
//
//            ArrayAdapter<String> adapter =
//
//        }catch(Exception e){
//            Log.e("detailpagelog", e.getMessage());
//        }
//

    }
}