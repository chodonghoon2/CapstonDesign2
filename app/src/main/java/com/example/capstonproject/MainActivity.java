package com.example.capstonproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import kotlinx.coroutines.TimeoutKt;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView; //바텀 네비게이션 뷰
    ActionMenuView actionMenuView;
    Button all_match_list_btn , my_match_list_btn;
    chomeFragment chomeFragment;
    cmatch_addFragment cmatch_addFragment;
    cmatchFragment cmatchFragment;


    String is_login, user_id, user_name, user_major;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new fab());


        //유저정보 스토리지
        SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);

        is_login = USERINFO.getString("is_login", "로그인 안됨");
        user_id = USERINFO.getString("id", "id 없음");
        user_name = USERINFO.getString("name", "이름 없음");
        user_major = USERINFO.getString("major", "학과 없음");

        //처음화면
        NavigationBarView navigationBarView = findViewById(R.id.bottomNavi);



        //오류확인
        Log.i("custom-home-log", USERINFO.getString("is_login", "로그인없음"));
        Log.i("custom-home-log", USERINFO.getString("id", "아이디없음"));
        Log.i("custom-home-log", USERINFO.getString("name", "이름없음"));
        Log.i("custom-home-log", USERINFO.getString("team", "팀없음"));

        if(is_login.equals("로그인상태")){
            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new chomeFragment(user_id, user_name, user_major)).commit();

        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new chomeFragment( "", "", "")).commit();

        }

        //바텀 네비게이션뷰 안의 아이템 설정
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_fragment:
                        //로그인 시도가 true일때만 유저정보 뜨게 하기, 로그인 안됐으면 빈칸
                        if(is_login.equals("로그인상태")){
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new chomeFragment(user_id, user_name, user_major)).commit();

                        }else{
                            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new chomeFragment( "유저아이디", "유저이름", "유저학과")).commit();

                        }
                        return true;
                    case R.id.match_add_fragment:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new cmatch_addFragment()).commit();
                        Intent intent = new Intent(getApplicationContext(),aadd_matching.class);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });

        navigationBarView.bringToFront();
    }  //oncreate

    class fab implements View.OnClickListener{
        @Override
        public void onClick(View view) {
         Intent intent = new Intent(getApplicationContext(), cNotice.class);
         startActivity(intent);
        }
    }




    //타이틀 메뉴바 관한 코드
    // 로그인 상태에 따라 레이아웃이 다르게 나오도록 만들기
    //그러려면 메뉴 레이아웃 추가로 만들기
    public boolean onCreateOptionsMenu(Menu menu){
        SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);
        if(!USERINFO.getString("is_login", "").equals("로그인상태")){
            //getMenuInflater().inflate(R.menu.c_menu_title_navi , menu);
            getMenuInflater().inflate(R.menu.c_menu_title_navi , menu);
        }else{
            //로그인 상태일때 들어갈 레이아웃
            getMenuInflater().inflate(R.menu.c_menu_title_navi2 , menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
// 추가로 버튼에 동작 넣을거면 if 해서 넣으면 됩니다.
        if( id == R.id.login_btn) {
            Intent intent = new Intent(getApplicationContext(), ylogin_page.class);
            startActivity(intent);
        }else if(id == R.id.my_info_btn){
            Intent intent = new Intent(getApplicationContext(), amyinfo.class);
            startActivity(intent);
        }else if(id == R.id.logout_btn){
            SharedPreferences USERINFO = getSharedPreferences("USERINFO", MODE_PRIVATE);
            SharedPreferences.Editor editor = USERINFO.edit();
            editor.clear().commit();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else if(id == R.id.setting_btn){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}