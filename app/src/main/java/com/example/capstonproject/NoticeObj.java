package com.example.capstonproject;

import android.os.SystemClock;
import android.util.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticeObj {
    String MYID, SENDID;
    public NoticeObj(String MYID, String SENDID){
        this.MYID = MYID;
        this.SENDID = SENDID;
    }

    public String sendToMSG(String Msg){
        String now;
        SystemClock.elapsedRealtime();
        try{
            yTask sendTask = new yTask("sendNotice");
            String result = sendTask.execute("&a=1&sendId=" + MYID + "&recvId=" + SENDID + "&message=" + Msg + "&send_time=" + "").get();
            Log.e("notice-log", result);

            if(result.equals("보내기성공")){
                return result;
            }

        }catch(Exception e){
            Log.e("notice-log", e.getMessage());
        }
        return "보내기실패";
    }


}
