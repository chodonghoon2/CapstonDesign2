package com.example.capstonproject;

import android.os.SystemClock;
import android.util.Log;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticeObj {
    String MYID;
    public NoticeObj(String MYID){
        this.MYID = MYID;
    }

    public String sendToMSG(String Msg, String SENDID){
        String now;
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

    public int count_notices(){
        try{
            yTask countTask = new yTask("countNotice");
            String result = countTask.execute("&a=1&myId=" + MYID).get();
            Log.e("notice-log", result);

            return Integer.parseInt(result);
        }catch(Exception e){
            Log.e("notice-log", e.getMessage());
        }

        return -1;
    }

    public String[] call_notice(){
        try{
            yTask callTask = new yTask("callNotices");
            String result = callTask.execute("&a=1&myId=" + MYID).get();
            Log.e("notice-log", result);
            // 보낸사람id, 메세지, 보낸시간 묶음으로 받음.
            String[] notice_list = result.split("/");

            return notice_list;
        }catch(Exception e){
            Log.e("notice-log", e.getMessage());
        }

        return null;
    }
}
