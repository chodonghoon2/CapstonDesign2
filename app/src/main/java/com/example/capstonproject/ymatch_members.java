package com.example.capstonproject;

import android.widget.Button;

public class ymatch_members {
    private String member_name, member_info, member_phone;
    private Button accept_btn, refuse_btn;
    String match_number, member_id;

    public ymatch_members(String match_number, String member_id, String member_name, String member_info, String member_phone){
        this.match_number = match_number;
        this.member_id = member_id;
        this.member_name = member_name;
        this.member_info = member_info;
        this.member_phone = member_phone;
    }

    public String getMatch_number(){
        return match_number;
    }

    public String getMember_id(){
        return member_id;
    }

    public String getMember_info() {
        return member_info;
    }

    public String getMember_name() {
        return member_name;
    }

    public String getMember_phone() {
        return member_phone;
    }

    public void setMatch_number(String match_number){
        this.match_number = match_number;
    }

    public void setMember_id(String member_id){
        this.member_id = member_id;

    }

    public void setMember_info(String member_info) {
        this.member_info = member_info;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public void setMember_phone(String member_phone) {
        this.member_phone = member_phone;
    }
}
