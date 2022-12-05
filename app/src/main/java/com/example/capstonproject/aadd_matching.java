package com.example.capstonproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ActionMenuView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;

public class aadd_matching extends AppCompatActivity {
    BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    ActionMenuView actionMenuView;
    Button buttonEvent;
    chomeFragment chomeFragment;
    cmatch_addFragment cmatch_addFragment;
    cmatchFragment cmatchFragment;
    EditText match_subject, edittext_persons;
    Button btn_ok;
    RadioGroup radio_groupsex, radio_groupexercise, radio_group_type;
    NumberPicker recruit_people, match_day, match_month;
    TimePicker match_time;
    Spinner match_major;

    String str_sex = "", str_exercise = "", str_type = "", str_month= "", str_day= "", str_hour= "", str_minute= "", str_time= "", str_major= "", str_people;

    ArrayList<String> majors;
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadd_matching);

        SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE); //영준
        //처음화면

        majors = new ArrayList<>();
        majors.add("컴퓨터공학과");
        majors.add("유아교육과");
        majors.add("신학과");
        majors.add("건축학과");
        majors.add("경영학과");
        majors.add("영어영문학과");
        majors.add("약학과");
        majors.add("간호학과");
        majors.add("물리치료학과");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, majors);


        //aad_matching 버튼 연결
        match_subject = (EditText) findViewById(R.id.match_subject);
        match_major = (Spinner) findViewById(R.id.match_major);
        radio_groupsex = (RadioGroup) findViewById(R.id.radio_groupsex);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        radio_groupexercise = (RadioGroup) findViewById(R.id.radio_groupexercise);
        radio_group_type = (RadioGroup) findViewById(R.id.radio_group_type);
        recruit_people = (NumberPicker) findViewById(R.id.recruit_people);
        match_month = (NumberPicker) findViewById(R.id.match_month);
        match_day = (NumberPicker) findViewById(R.id.match_day);
        match_time = (TimePicker) findViewById(R.id.match_time);

        //match_major 어뎁터
        match_major.setAdapter(adapter);

        //모집인원 설정
        recruit_people.setMaxValue(11);
        recruit_people.setMinValue(1);

        //매칭 날짜 설정
        match_month.setMaxValue(12);
        match_month.setMinValue(1);
        match_day.setMaxValue(31);
        match_day.setMinValue(1);


        recruit_people.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                str_people = "";
                str_people += newVal;
            }
        });

        match_month.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                str_month = "";
                if(newVal>=10){
                    str_month += newVal;
                }else{
                    str_month += "0" + newVal;
                }
            }
        });

        match_day.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                str_day = "";
                if(newVal>=10){
                    str_day += newVal;
                }else{
                    str_day += "0" + newVal;
                }
            }
        });

        //시간설정
        match_time.setIs24HourView(true);
        match_time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {


                if(hourOfDay>=10){
                    str_hour = "";
                    str_hour += hourOfDay;
                }else{
                    str_hour = "";
                    str_hour += "0" + hourOfDay;
                }

                if(minute >= 10){
                    str_minute = "";
                    str_minute += minute;
                }else{
                    str_minute = "";
                    str_minute += "0" + minute;
                }

            }
        });


        radio_groupsex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_btnman:
                        str_sex = "male";
                        break;
                    case R.id.rg_btnfemale:
                        str_sex = "female";
                        break;
                }
            }
        });

        radio_groupexercise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_btnsoccer:
                        str_exercise = "축구";
                        break;
                    case R.id.rg_btnfootball:
                        str_exercise = "풋살";
                        break;
                }
            }
        });

        radio_group_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rg_btn_mercenary:
                        str_type = "용병";
                        break;
                    case R.id.rg_btn_rival:
                        str_type = "상대팀";
                        break;
                }
            }
        });

        match_major.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_major = majors.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                str_major = "학과 없음";
            }
        });




        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_time = str_month + str_day + str_hour + str_minute;


                try{
                    yTask summit_task = new yTask("addMatching");
                    String result;
                    StringBuilder param = new StringBuilder();
                    param.append("&a='1'," + "&match_owner=" + USERINFO.getString("id", "")
                            + "&match_title=" + match_subject.getText() + "&exercise_type=" + str_exercise
                            + "&match_type=" + str_type + "&match_time=" + str_time + "&match_persons=" + str_people
                            + "&match_sex=" + str_sex+ "&match_major=" + str_major);

                    result =  summit_task.execute(param.toString()).get();
                    Log.i("addMatching-customLog", result);

                    if(result.equals("삽입성공")){

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                    }
                }catch(Exception e){
                    Log.i("addMatching-customLog", e.getMessage());
                }


            }
        });



    }

}