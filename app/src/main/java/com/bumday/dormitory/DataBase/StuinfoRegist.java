package com.bumday.dormitory.database;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StuinfoRegist extends AsyncTask<String, Void, String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;

            // [IP변경시 수정]접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL("http://192.168.25.254:8080/AndroidDB3/androidDB.jsp");

            // [수정X]연결과 관련된 코드
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            ////////////////////////////////////데이터전송/////////////////////////////////////////////
            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "id=" + strings[0] + "&name=" + strings[1] + "&pwd=" + strings[2] + "&address=" + strings[3] + "&major=" + strings[4] + "&telnum=" + strings[5] + "&dorm=" + strings[6] + "&roomnum=" + strings[7];

            osw.write(sendMsg);
            osw.flush();

            //////////////////////////////////연결설정//////////////////////////////////////////////
            //[수정X]jsp와 통신 성공 시 수행
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                // [수정X] jsp에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
            } else {
                // 통신 실패
                Log.i("통신 결과", conn.getResponseCode()+"에러");
            }
            //예외처리
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //jsp로부터 받은 리턴 값
        return receiveMsg;
    }
}