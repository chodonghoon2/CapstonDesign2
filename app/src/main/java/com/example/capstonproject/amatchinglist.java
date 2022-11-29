package com.example.capstonproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class amatchinglist extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private aMyRecyclerAdapter mRecyclerAdapter;
    private ArrayList<aFriendItem> mfriendItems;
    private RecyclerView.LayoutManager mLayoutManager;
    HashMap<String, String> matchMap = new HashMap<String, String>();
    Set<String> keySet = matchMap.keySet();
    String[] m_values;
    String match_value = "";
    String[] matchlist;
    String[] match_tag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chome);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        /* initiate adapter */
        mRecyclerAdapter = new aMyRecyclerAdapter();

        /* initiate recyclerview */
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        try{
            String request; //모든 매칭 정보 요청
            yTask ytask = new yTask("match_info");
            String result = ytask.execute("&a=1"+ "&user_id=" + "bugi").get();
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


        /* adapt data */
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
    }
}
