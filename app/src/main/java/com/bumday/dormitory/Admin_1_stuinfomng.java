package com.bumday.dormitory;


import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumday.dormitory.database.RequestHttpURLConnection;
import com.bumday.dormitory.database.StuinfoRegist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Admin_1_stuinfomng extends FragmentActivity {

    EditText id_et, name_et, pwd_et, address_et, major_et, telnum_et, dorm_et, roomnum_et;

    // Json을 담을 문자
    String stujson;

    // JSON을 담을 List
    private ArrayList<String> idList, nameList, pwdList, addressList, majorList, telnumList, dormList, roomnumList;

    public ArrayList<String> items;
    public ArrayAdapter adapter;
    ListView listview;



    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_1_stuinfomng);

        // DB읽어오는 연결설정
        String url = "http://192.168.25.254:8080/AndroidDB3/androidDBJSON.jsp";
        final NetworkTask networkTask = new NetworkTask(url, null); // AsyncTask를 통해 HttpURLConnection 수행.
        networkTask.execute();

        //리스트 초기화
        idList = new ArrayList<>();
        nameList = new ArrayList<>();
        pwdList = new ArrayList<>();
        addressList = new ArrayList<>();
        majorList = new ArrayList<>();
        telnumList = new ArrayList<>();
        dormList = new ArrayList<>();
        roomnumList = new ArrayList<>();
        // 어답터설정
        // 빈 데이터 리스트 생성.
        items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        adapter = new ArrayAdapter(this, R.layout.listview_text, items) ;
        // listview 초기화 및 adapter 지정.
        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter) ;

        //로고 누르면, 메인화면으로 전환
        ImageView logo = (ImageView) findViewById(R.id.logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_1_stuinfomng.this, Admin_0_main.class);
                startActivity(intent);
                finish();
            }
        });
        // EditText ID따기
        id_et = (EditText)findViewById(R.id.et_id);
        name_et = (EditText)findViewById(R.id.et_name);
        pwd_et = (EditText)findViewById(R.id.et_pwd);
        address_et = (EditText)findViewById(R.id.et_address);
        major_et = (EditText)findViewById(R.id.et_major);
        telnum_et = (EditText)findViewById(R.id.et_telnum);
        dorm_et = (EditText)findViewById(R.id.et_dorm);
        roomnum_et = (EditText)findViewById(R.id.et_roomnum);




        /** 생성 버튼 이벤트 **/
        Button btn_confirm = (Button)findViewById(R.id.btn_comfirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                items.clear();

                // Edittext값 읽어서 DB에 input
                try {
                    String result;
                    String id = id_et.getText().toString();
                    String name = name_et.getText().toString();
                    String pwd = pwd_et.getText().toString();
                    String address = address_et.getText().toString();
                    String major = major_et.getText().toString();
                    String telnum = telnum_et.getText().toString();
                    String dorm = dorm_et.getText().toString();
                    String roomnum = roomnum_et.getText().toString();

                    StuinfoRegist task = new StuinfoRegist();
                    result = task.execute(id, name, pwd, address, major, telnum, dorm, roomnum).get();

                    //결과 토스트출력
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Log.i("DBtest", ".....ERROR.....!");
                }




//                Student studentjson = new Student();
//                studentjson.jsonParsing(stujson);
                // ListView에 select
                try {
                    JSONObject jsonobject = new JSONObject(stujson);
                    JSONArray jsonarray = jsonobject.getJSONArray("rows");

                    for(int i = 0 ; i<jsonarray.length(); i++){

                        JSONObject jsonObject = jsonarray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String pwd = jsonObject.getString("pwd");
                        String address = jsonObject.getString("address");
                        String major = jsonObject.getString("major");
                        String telnum = jsonObject.getString("telnum");
                        String dorm = jsonObject.getString("dorm");
                        String roomnum = jsonObject.getString("roomnum");
                        idList.add(id);
                        nameList.add(name);
                        pwdList.add(pwd);
                        addressList.add(address);
                        majorList.add(major);
                        telnumList.add(telnum);
                        dormList.add(dorm);
                        roomnumList.add(roomnum);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                items.clear();
//                adapter.notifyDataSetChanged();

                for (int i=0; i < idList.size(); i++) {

                    items.add("id:"+ idList.get(i) + "/ name: " + nameList.get(i) + "/ pwd: " + pwdList.get(i) + "/ address: " + addressList.get(i)
                            + "/ major: " + majorList.get(i) + "/ telnum: " + telnumList.get(i) + "/ drom: " + dormList.get(i) + "/ roomnum: " + roomnumList.get(i));
                }
                //바뀐리스트 최신화
                adapter.notifyDataSetChanged();
            }
        });

//                String stunums = id.getText().toString();
//                String names =  name.getText().toString();
//                String passwords = pwd.getText().toString();
//                String addresss = address.getText().toString();
//                String majors = major.getText().toString();
//                String telnums = telnum.getText().toString();
//                int count;
//                count = adapter.getCount();
//
//                // 아이템 추가.
//                items.add("학번 :"+stunums+ "/이름 :" +names+ "/비밀번호 :"+passwords+ "/거주지 :"+addresss+ "/전공 :"+majors+ "/연락처 :" +telnums);
//
//                // listview 갱신
//                adapter.notifyDataSetChanged();

    }

//    public void Sync(){
//
//        Student stuinfo = new Student();
//
//        //listview 갱신
//        adapter.notifyDataSetChanged();
//
//    }


    //뒤로가기 두번 연속=종료
    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            finish();
        }
    }


    //DB 읽어오기 실행
    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            stujson = s;
        }
    }
}
