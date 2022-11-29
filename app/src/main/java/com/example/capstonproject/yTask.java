package com.example.capstonproject;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class yTask extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;
    String connJSP;

    yTask(String connJsp){
        super();
        this.connJSP = connJsp;
    }

    @Override
    protected String doInBackground(String... strings){
        try{
            String str;
            URL url = new URL("http://10.0.2.2:8111/Capstonserver2/"+ connJSP +".jsp");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("Accept", "application/xml"); //이거를 박으면 xml코드로 리턴받음?
            conn.setDoOutput(true); //outputstream으로 데이터를 넘기기로 함

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            sendMsg = "yMSG="+strings[0];


            osw.write(sendMsg);//OutputStreamWriter에 담아 전송합니다.
            osw.flush();



            //jsp와 통신이 정상적으로 되었을 때 할 코드들입니다.
            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                //jsp에서 보낸 값을 받겠죠?
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();

            } else {
                Log.i("통신 결과", conn.getResponseCode()+"에러");
                // 통신이 실패했을 때 실패한 이유를 알기 위해 로그를 찍습니다.
            }
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        return receiveMsg;
    }
}
