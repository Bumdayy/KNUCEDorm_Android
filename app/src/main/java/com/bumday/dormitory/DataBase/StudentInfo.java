package com.bumday.dormitory.DataBase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
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

public class StudentInfo{
    private static String TAG = "DATABASE_STUDENT";

    private static final String TAG_JSON="Student";
    private static final String TAG_ID = "stu_num";
    private static final String TAG_NAME = "name";
    private static final String TAG_PW = "pw";
    private static final String TAG_ADDRESS ="address";
    private static final String TAG_MAJOR = "major";
    private static final String TAG_PHONE ="phone";
    private static final String TAG_KIND = "dom_kind";
    private static final String TAG_ROOM = "room";

    ArrayList<Student> totalList = new ArrayList<Student>();
    HashMap<String,String> loginList = new HashMap<>();
    String mJsonString;
    Activity activity;



    public StudentInfo(Activity activity)
    {
        this.activity=activity;
        GetData task = new GetData();
        task.execute("http://purpleu.shop/real.php");
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

            if (result == null){
                Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show();
            }
            else {
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
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line;

                while((line = bufferedReader.readLine()) != null){
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


    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject(i);
                Student student = new Student();
                student.setId(item.getString(TAG_ID));
                student.setName(item.getString(TAG_NAME));
                student.setPw(item.getString(TAG_PW));
                student.setAddress(item.getString(TAG_ADDRESS));
                student.setMajor(item.getString(TAG_MAJOR));
                student.setPhone(item.getString(TAG_PHONE));
                student.setDom(item.getString(TAG_KIND));
                student.setRoom(item.getString(TAG_ROOM));

                loginList.put(student.getId(),student.getPw());
                totalList.add(student);
            }

        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }

    }
    public HashMap<String, String> logininfo()
    {
        return loginList;
    }

    public ArrayList<Student> totalinfo()
    {
        return totalList;
    }
    public Student searchInfo(String id)
    {
        Student student = new Student();
        for(int i=0;i<totalList.size();i++)
        {
            if((totalList.get(i)).getId().equals(id))
            {
                student=totalList.get(i);
                break;
            }
        }
        return student;
    }
}
