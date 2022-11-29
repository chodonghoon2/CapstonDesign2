package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ysignup_page extends AppCompatActivity {

    TextView confirm_info;
    Button confirm_id, confirm_phone, btn_submit;
    EditText user_id, user_pw, user_pw2, user_name, user_major, user_phone;
    RadioGroup sex_group;

    String str_sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysignup_page);

        confirm_info = (TextView) findViewById(R.id.confirm_info);
        confirm_id = (Button) findViewById(R.id.confirm_id);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        confirm_id = (Button) findViewById(R.id.confirm_id);
        confirm_phone = (Button) findViewById(R.id.confirm_phone);
        user_id = (EditText) findViewById(R.id.user_id);
        user_pw = (EditText) findViewById(R.id.user_pw);
        user_pw2 = (EditText) findViewById(R.id.user_pw2);
        user_name = (EditText) findViewById(R.id.user_name);
        user_major = (EditText) findViewById(R.id.user_major);
        user_phone = (EditText) findViewById(R.id.user_phonenumber);
        sex_group = (RadioGroup) findViewById(R.id.sex_group);

        boolean id_ok, phone_ok;

        confirm_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //id중복확인
                yTask task = new yTask("signup");
                try{
                    String is_overlapping = task.execute("a = '1'&id=" + user_id.getText()).get();

                    if(is_overlapping.equals("중복")){
                        confirm_info.setText("아이디 중복");
                        confirm_info.setTextColor(Color.parseColor("#FF9999"));
                        user_id.setTextColor(Color.parseColor("#FF9999"));
                    }else{
                        confirm_info.setText("사용 가능한 아이디");
                        user_id.setTextColor(Color.parseColor("#66FF66"));
                    }

                }catch (Exception e){
                    Log.i("loginpage custom-log",e.getMessage());
                }
            }
        });



        sex_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.male:
                        str_sex = "male";
                        break;
                    case R.id.female:
                        str_sex = "female";
                        break;
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yTask task = new yTask("signup");
                try{
                    String result =  task.execute("a = '1'&id=" + user_id.getText()
                            + "&pw=" + user_pw.getText()
                            + "&name=" + user_name.getText()
                            + "&sex=" +  str_sex
                            + "&phone=" + user_phone.getText()
                            + "&major" + user_major.getText()).get();

                    if(result.equals("성공")){
                        confirm_info.setText("회원가입 성공");
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }else{
                        Log.i("loginpage custom-log", result);
                        confirm_info.setTextColor(Color.parseColor("#FF9999"));
                        confirm_info.setText("회원가입 정보를 확인해주세요.");
                    }

                }catch (Exception e){
                    Log.i("loginpage custom-log",e.getMessage());
                }

            }
        });
    }
}