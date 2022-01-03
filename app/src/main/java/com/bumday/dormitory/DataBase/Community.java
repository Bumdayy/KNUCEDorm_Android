package com.bumday.dormitory.DataBase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
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
import java.util.LinkedHashMap;
import java.util.List;

public class Community implements Parcelable{
    private static String TAG = "DATABASE_COMMUNITY";

    private static final String TAG_JSON="Community";
    private static final String TAG_TITLE = "title";
    private static final String TAG_POST = "post";
    private static final String TAG_NAME = "name";
    private static final String TAG_ID = "stu_num";
    private static final String TAG_TIME ="time";

    ArrayList<Posts> ListInfo = new ArrayList<Posts>();

    GetData task;
    String mJsonString;
    Activity activity;
    Boolean result=false;

    public Community(Activity activity)
    {
        this.activity=activity;
        task = new GetData();
        task.execute("http://purpleu.shop/community.php");
        result=true;
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

    protected Community(Parcel in) {
        ListInfo = in.createTypedArrayList(Posts.CREATOR);
        mJsonString = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(ListInfo);
        dest.writeString(mJsonString);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Community> CREATOR = new Creator<Community>() {
        @Override
        public Community createFromParcel(Parcel in) {
            return new Community(in);
        }

        @Override
        public Community[] newArray(int size) {
            return new Community[size];
        }
    };

    private void showResult(){
        try {
            JSONObject jsonObject = new JSONObject(mJsonString);
            JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);

            for(int i=0;i<jsonArray.length();i++){

                JSONObject item = jsonArray.getJSONObject( i);
                Posts posts = new Posts();
                posts.setNum(i+1);
                posts.setTitle(item.getString(TAG_TITLE));
                posts.setPost(item.getString(TAG_POST));
                posts.setId(item.getString(TAG_ID));
                posts.setName(item.getString(TAG_NAME));
                posts.setTime(item.getString(TAG_TIME));
                ListInfo.add(posts);
            }

        } catch (JSONException e) {
            Log.d(TAG, "showResult : ", e);
        }

    }
    public ArrayList<Posts> listInfo()
    {
        return ListInfo;
    }
    public ArrayList<Posts> addList(Posts posts)
    {
        ListInfo.add(posts);
        return ListInfo;
    }
}
