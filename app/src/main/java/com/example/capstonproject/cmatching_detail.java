package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class cmatching_detail extends AppCompatActivity {
    TextView creater_id, match_title, match_persons, match_month, match_day, match_hour, match_minute, numberOfMember;
    ImageView match_type, match_sex, exercise_type, match_major;
    Button apply_btn;
    ListView member_listview;

    private Intent intent;
    Boolean my_match;
    String match_number, result;
    String[] matchInformation;
    String str_creater_id, str_match_title, str_exercise_type, str_match_type, str_match_time, str_recruit_person, str_match_sex, str_match_major;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmatching_detail);


        intent = getIntent();
        match_number = intent.getStringExtra("match_number");
        my_match = intent.getBooleanExtra("is_myMatch", false);
        Log.e("custom", "" + match_number);
//

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);

        creater_id = (TextView) findViewById(R.id.creater_id);
        match_title = (TextView) findViewById(R.id.match_title);
        match_type = (ImageView) findViewById(R.id.match_type);
        match_sex = (ImageView) findViewById(R.id.match_sex);
        exercise_type = (ImageView) findViewById(R.id.exercise_type);
        match_major = (ImageView) findViewById(R.id.match_major);
        match_persons = (TextView) findViewById(R.id.match_people);
        match_month = (TextView) findViewById(R.id.time_month);
        match_day = (TextView) findViewById(R.id.time_day);
        match_hour = (TextView) findViewById(R.id.time_hour);
        match_minute = (TextView) findViewById(R.id.time_minute);
        apply_btn = (Button) findViewById(R.id.apply_btn);
        numberOfMember = (TextView) findViewById(R.id.numberOfMember);
        member_listview = (ListView) findViewById(R.id.member_list);


        try {
            yTask request = new yTask("matchInformation");
            result = request.execute("&a=1&match_number=" + match_number).get();

            matchInformation = result.split(":")[1].split(",");
            str_creater_id = matchInformation[0];
            str_match_title = matchInformation[1];
            str_exercise_type = matchInformation[2];
            str_match_type = matchInformation[3];
            str_match_time = matchInformation[4];
            str_recruit_person = matchInformation[5];
            str_match_sex = matchInformation[6];
            str_match_major = matchInformation[7];

            Log.e("detail-customlog", "???????????? ??????");


            creater_id.setText(str_creater_id);
            match_title.setText(str_match_title);

            if (str_match_type.equals("??????")) {
                match_type.setImageResource(R.drawable.merecenary);
            } else {
                match_type.setImageResource(R.drawable.rivals);
            }

            if (str_match_sex.equals("female")) {
                match_sex.setImageResource(R.drawable.female);
            } else {
                match_sex.setImageResource(R.drawable.male);
            }

            switch (str_exercise_type) {
                case "??????":
                    exercise_type.setImageResource(R.drawable.soccer);
                    break;
                case "??????":
                    exercise_type.setImageResource(R.drawable.footsal);
                    break;
            }

            switch (str_match_major) {
                case "?????????????????????":
                    match_major.setImageResource(R.drawable.major_coumputer);
                    break;
                case "??????????????????":
                    match_major.setImageResource(R.drawable.major_english);
                    break;
                case "???????????????":
                    match_major.setImageResource(R.drawable.major_child);
                    break;
                case "?????????":
                    match_major.setImageResource(R.drawable.major_christian);
                    break;
                case "????????????":
                    match_major.setImageResource(R.drawable.major_economy);
                    break;
                case "?????????":
                    match_major.setImageResource(R.drawable.major_medicine);
                    break;
                case "????????????":
                    match_major.setImageResource(R.drawable.major_nurse);
                    break;
                case "??????????????????":
                    match_major.setImageResource(R.drawable.major_phsical);
                    break;
            }

            match_persons.setText(str_recruit_person);

            match_month.setText(str_match_time.substring(0, 2));
            match_day.setText(str_match_time.substring(2, 4));
            match_hour.setText(str_match_time.substring(4, 6));
            match_minute.setText(str_match_time.substring(6));


            //?????? ????????? ???????????? ???????????? ???????????? ??????
            if (my_match) {
                apply_btn.setVisibility(View.INVISIBLE);
                //?????????
                y_members_adapter adapter = new y_members_adapter();
                member_listview.setAdapter(adapter);

                // ?????? ??????
                yTask phoneTask = new yTask("members_phone");
                String phones = phoneTask.execute("&a=1&match_number=" + match_number).get();
                String[] memberlist = phones.split("/");
                Log.e("detail-customlog", phones);
                String NOM = "????????? - " + memberlist.length + "??? ?????????";
                numberOfMember.setText(NOM);

                for (String member : memberlist) {
                    Log.e("detail-customlog", member);
                    //user_name, user_phone, user_major
                    String[] memInfo = member.split(",");
                    adapter.addItem(match_number, "", memInfo[0], memInfo[1], memInfo[2]);
                }

            }


        } catch (Exception e) {
            Log.e("detail-customlog", e.getMessage());
        }

        member_listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);
                String user_id = USERINFO.getString("id", "");
                try {
                    yTask apply_request = new yTask("matchApply");
                    String result = apply_request.execute("&a=1&match_number=" + match_number + "&user_id=" + user_id).get();
                    if (result.equals("??????????????????")) {
                        Log.e("detailpagelog", "????????????");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        Log.e("detailpagelog", "????????????");

                    }

                    //notice ??????
                    try {
                        String result2 = new NoticeObj(user_id).sendToMSG(user_id + "?????? ????????? ??????????????????.", str_creater_id);
                        Log.e("detailpagelog", result2);
                    } catch (Exception e) {
                        Log.e("detailpagelog", e.getMessage());
                    }
            }catch(Exception e){
                Log.e("detailpagelog", e.getMessage());
            }

        }
    });
}}



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

