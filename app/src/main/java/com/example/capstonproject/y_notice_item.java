package com.example.capstonproject;

public class y_notice_item {
    private String msg, send_id, send_time;

    public y_notice_item(String msg, String send_id, String send_time){
        this.msg=msg;
        this.send_id=send_id;
        this.send_time=send_time;
    }

    public String getMsg() {
        return msg;
    }

    public String getSend_id() {
        return send_id;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSend_id(String send_id) {
        this.send_id = send_id;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }
}
