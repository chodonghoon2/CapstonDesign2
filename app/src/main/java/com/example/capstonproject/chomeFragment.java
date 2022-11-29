package com.example.capstonproject;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class chomeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private aMyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<aFriendItem> mfriendItems;
    String id, name, major;
    Button all_match_list_btn, my_match_list_btn;
    Context ct;

    TextView  user_name, user_major;
    public chomeFragment(String id, String name, String major){
        super();
        this.id = id;
        this.name = name;
        this.major = major;
    }


    String[] matchlist;
    String[] match_tag;
    HashMap<String, String> matchMap = new HashMap<String, String>();
    Set<String> keySet = matchMap.keySet();
    String match_value = "";
    String[] m_values;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //매칭정보 요청
        try{
            String request; //모든 매칭 정보 요청
            yTask ytask = new yTask("match_info");
            String result = ytask.execute("&a=1"+ "&user_id=" + id).get();
            //매칭번호: 생성자이름, 제목 순서, 성별
            //매칭정보 저장
            matchlist = result.split("/");
            for(String matchs : matchlist){
                match_tag = matchs.split(":");
                matchMap.put(match_tag[0], match_tag[1]);
            }

        }catch (Exception e){
            Log.i("chome-customLog", e.getMessage());
        }
        //USERINFO = getSharedPreferences("Userinfo", MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_chome, container, false);

        all_match_list_btn = (Button) view.findViewById(R.id.all_match_list_btn);
        my_match_list_btn = (Button) view.findViewById(R.id.my_match_list_btn);


        user_name = (TextView) view.findViewById(R.id.user_id);
        user_name.setText(name);
        user_major = (TextView) view.findViewById(R.id.user_major);
        user_major.setText(major);




        mRecyclerView = (RecyclerView) view.findViewById(R.id.match_list_container);

        mRecyclerAdapter = new aMyRecyclerAdapter();


        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ct = container.getContext()));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ct = container.getContext(), RecyclerView.VERTICAL,false));

        mfriendItems = new ArrayList<>();

        //


        for(String matchs : keySet){
            match_value = matchMap.get(matchs);
            //matchs -> 매칭 번호
            //[0] : 생성자 이름, [1]: 제목, [2]: 성별
            m_values = match_value.split(",");

            if(m_values[2].equals("female")) {
                mfriendItems.add(new aFriendItem(matchs, R.drawable.afemaleimage, m_values[0], m_values[1]));
            }
            else {
                mfriendItems.add(new aFriendItem(matchs, R.drawable.amerecenaryimage, m_values[0], m_values[1]));
            }
        }

        mRecyclerAdapter.setFriendList(mfriendItems);






        all_match_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result;
                try{
                    matchMap.clear();
                    String request; //모든 매칭 정보 요청
                    yTask ytask = new yTask("match_info");
                    result = ytask.execute("&a=1"+ "&user_id=" + id).get();
                    //매칭번호: 생성자이름, 제목 순서, 성별
                    //매칭정보 저장
                    matchlist = result.split("/");
                    for(String matchs : matchlist){
                        match_tag = matchs.split(":");
                        matchMap.put(match_tag[0], match_tag[1]);

                    }
                }catch (Exception e){
                    Log.i("chome-allmatchlistLog", e.getMessage());
                }


                //매칭리스트 reload시점
                mfriendItems.clear();
                for(String matchs : keySet){
                    match_value = matchMap.get(matchs);
                    //[0] : 생성자 이름, [1]: 제목, [2]: 성별
                    m_values = match_value.split(",");

                    if(m_values[2].equals("female")) {

                        mfriendItems.add(new aFriendItem(matchs, R.drawable.afemaleimage, m_values[0], m_values[1]));
                    }
                    else {
                        mfriendItems.add(new aFriendItem(matchs, R.drawable.amerecenaryimage, m_values[0], m_values[1]));
                    }
                }
                mRecyclerAdapter.setFriendList(mfriendItems);

            }
        });







        my_match_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result;
                try{
                    matchMap.clear();
                    SharedPreferences USERINFO = ct.getSharedPreferences("USERINFO", MODE_PRIVATE);
                    String request; //내 매칭만 정보 요청
                    //매칭번호: 생성자이름, 제목 순서, 성별
                    //매칭정보 저장
                    yTask ytask = new yTask("my_match_info");
                    result = ytask.execute("&a=1"+ "&user_id=" + id).get();

                    matchlist = result.split("/");
                    for(String matchs : matchlist){
                        match_tag = matchs.split(":");
                        matchMap.put(match_tag[0], match_tag[1]);
                    }
                }catch (Exception e){
                    Log.i("chome-mymatchlistLog", e.getMessage());
                }


                //매칭리스트 reload시점
                mfriendItems.clear();
                for(String matchs : keySet){
                    match_value = matchMap.get(matchs);
                    //[0] : 생성자 이름, [1]: 제목, [2]: 성별
                    m_values = match_value.split(",");

                    if(m_values[2].equals("female")) {

                        mfriendItems.add(new aFriendItem(matchs, R.drawable.afemaleimage, m_values[0], m_values[1]));
                    }
                    else {
                        mfriendItems.add(new aFriendItem(matchs, R.drawable.amerecenaryimage, m_values[0], m_values[1]));
                    }
                }
                mRecyclerAdapter.setFriendList(mfriendItems);

            }
        });


        return view;
    }
}