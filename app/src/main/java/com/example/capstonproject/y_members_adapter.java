package com.example.capstonproject;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class y_members_adapter extends BaseAdapter {
    private ArrayList<ymatch_members> members_list = new ArrayList<ymatch_members>();

    public y_members_adapter(){}

    @Override
    public int getCount() {
        return members_list.size();
    }

    public Object getItem(int position){
        return members_list.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View view, ViewGroup parent){
        Context ct = parent.getContext();

        //아이템 레이아웃 생성
        LayoutInflater inflater = (LayoutInflater) ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.ymatch_members_item, parent, false);

        ImageButton accept_btn = (ImageButton) view.findViewById(R.id.accepted);
        ImageButton refuse_btn = (ImageButton) view.findViewById(R.id.rejected);
        TextView member_name = (TextView) view.findViewById(R.id.member_name);
        TextView member_info = (TextView) view.findViewById(R.id.member_info);
        TextView member_phone = (TextView) view.findViewById(R.id.member_phone);

        ymatch_members member_item = members_list.get(position);

        String match_number = member_item.getMatch_number();
        String member_id = member_item.getMember_id();
        member_name.setText(member_item.getMember_name());
        member_info.setText(member_item.getMember_info());
        member_phone.setText(member_item.getMember_phone());

        
        //수락버튼 클릭시
        accept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    yTask accpetTask = new yTask("match_accept");
                    //보낼 파라미터 = match_number, member_id
                    String result = accpetTask.execute("&a=1&match_number="+ match_number+"&member_id=" + member_id).get();
                    // 결과 = 참여성공, 참여실패
                    Log.e("참여시도", result);




                    if(result.equals("참여성공")){
                        Intent intent = new Intent(ct, MainActivity.class);
                        ct.startActivity(intent);
                    }
                }catch (Exception e){
                    Log.e("member-adapter", e.getMessage());
                }
            }
        });

        
        //거절버튼 클릭시
        refuse_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    yTask refuseTask = new yTask("match_refuse");
                    //보낼 파라미터 = match_number, member_id
                    String result = refuseTask.execute("&a=1&match_number=" + match_number + "&member_id=" + member_id ).get();
                    // 결과 = 거절성공, 거절실패
                    Log.e("참여거절시도", result);
                    if(result.equals("삭제성공")){
                        Intent intent = new Intent(ct, MainActivity.class);
                        ct.startActivity(intent);
                    }
                }catch (Exception e){
                    Log.e("member-adapter", e.getMessage());
                }
            }
        });
        return view;
    }

    public void addItem(String match_number, String member_id, String member_name, String member_info, String member_phone){
        ymatch_members member_item = new ymatch_members(match_number, member_id, member_name, member_info, member_phone);
        members_list.add(member_item);
    }

    public void delItem(int position){
        members_list.remove(position);
    }
}
