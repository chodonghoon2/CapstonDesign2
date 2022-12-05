package com.example.capstonproject;

import android.widget.Button;

public class ymatch_members {
    private String member_name, member_info, member_phone;
    private Button accept_btn, refuse_btn;

    public ymatch_members(String member_name, String member_info, String member_phone){
        this.member_name = member_name;
        this.member_info = member_info;
        this.member_phone = member_phone;
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
