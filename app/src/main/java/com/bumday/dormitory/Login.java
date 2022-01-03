package com.bumday.dormitory;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumday.dormitory.DataBase.Student;
import com.bumday.dormitory.DataBase.StudentInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Login extends FragmentActivity {
    String admin = "777777777";
    String autoName, autoPwd, autoId;
    String getid ,getpassword;
    CheckBox checkBox;
    HashMap<String, String> idnpw;
    ArrayList<Student> total;
    StudentInfo sinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView login = (TextView) findViewById(R.id.login);
        final EditText id = (EditText) findViewById(R.id.id);
        final EditText password = (EditText) findViewById(R.id.password);
        checkBox = (CheckBox) findViewById(R.id.autologin);


        //학생정보 데이터 베이스를 가져오기 위한 클래스
        sinfo = new StudentInfo(this);

        //자동 로그인 구현을 위한 소스
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

        autoName = auto.getString("autoName", null);
        autoPwd = auto.getString("autoPwd", null);
        autoId = auto.getString("autoId",null);

        //id및 pw를 저장한 HashMap
        idnpw = sinfo.logininfo();
        total = sinfo.totalinfo();
        //자동 로그인 여부 확인
        if (autoName != null && autoPwd != null) {
            Toast.makeText(Login.this, autoName+"님 환영합니다.", Toast.LENGTH_SHORT).show();
            moveActivity(Stu_0_main.class,autoId);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getid = id.getText().toString();
                getpassword = password.getText().toString();

                //만일 admin 아이디로 진입시, [관리자모드] // 아니면 [학생모드]
                if (getid.equals(admin) && getpassword.equals(admin)) {
                    autoLogin("관리자");
                    moveActivity(Admin_0_main.class,admin);
                } else {
                    confirm();
                }
            }
        });
    }

    //로그인 확인
    private void confirm() {
        Set set = idnpw.keySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String pw = idnpw.get(key);

            if (key.equals(getid) && pw.equals(getpassword)) {
                Toast.makeText(Login.this, searchName(key)+"님 환영합니다.", Toast.LENGTH_SHORT).show();
                autoLogin(searchName(key));
                moveActivity(Stu_0_main.class,key);
                return;
            }
        }
        Toast.makeText(Login.this, "학번 또는 비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
    }

    //자동 로그인 기능
    private void autoLogin(String name)
    {
        if (checkBox.isChecked()) {
            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

            SharedPreferences.Editor autoLogin = auto.edit();
            autoLogin.putString("autoId", getid);
            autoLogin.putString("autoName", searchName(getid));
            autoLogin.putString("autoPwd", getpassword);
            autoLogin.commit();
        }
    }

    //id에 따른 이름값 출력
    private String searchName(String key)
    {
        Student student = sinfo.searchInfo(key);
        return student.getName();
    }

    //Activity간 이동
    private void moveActivity(Class where,String key)
    {
        Intent intent = new Intent(Login.this, where);
        intent.putExtra("학번",key);
        startActivity(intent);
        finish();
    }

    //뒤로가기 두번 연속=종료
    private long time = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show();
        } else if (System.currentTimeMillis() - time < 2000) {
            finishAffinity();
        }
    }
}
