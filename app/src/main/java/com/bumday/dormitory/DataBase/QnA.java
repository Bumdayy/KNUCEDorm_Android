package com.bumday.dormitory.DataBase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class QnA extends StudentInfo {
    private static String TAG = "DATABASE_QNA";

    private static final String TAG_JSON = "QnA";
    private static final String TAG_KINDS = "kinds";
    private static final String TAG_DOM_KINDS = "dom_kind";
    private static final String TAG_ROOM = "room";
    private static final String TAG_POST = "post";
    private static final String TAG_TIME = "time";
    private static final String TAG_ID = "stu_num";

    ArrayList<String[]> totalList = new ArrayList<String[]>();
    String mJsonString;
    Activity activity;

    QnA(Activity activity) {
        super(activity);
        this.activity = activity;
        GetData task = new GetData();
        task.execute("http://purpleu.shop/qna.php");
    }

    private class GetData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;
        String errorString = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(activity,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
//            Toast.makeText(activity, "DB 연동 완료", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "response  - " + result);

            if (result == null) {
                Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show();
            } else {
                mJsonString = result;
                showResult();
            }
        }


        @Override
        protected String doInBackground(String... params) {

            String serverURL = params[0];


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString().trim();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);
                errorString = e.toString();

                return null;
            }

        }
    }


    private void showResult() {
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String kinds = item.getString(TAG_KINDS);
                String dom_kind = item.getString(TAG_DOM_KINDS);
                String room = item.getString(TAG_ROOM);
                String post = item.getString(TAG_POST);
                String time = item.getString(TAG_TIME);
                String stu_num = item.getString(TAG_ID);

                totalList.add(new String[]{kinds, dom_kind, room, post, time,stu_num});
            }

        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }

    }

    public HashMap<String, String> logininfo() {
        return loginList;
    }

}


