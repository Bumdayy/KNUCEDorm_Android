package com.bumday.dormitory;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumday.dormitory.DataBase.Community;
import com.bumday.dormitory.DataBase.StudentInfo;

public class Stu_0_main extends FragmentActivity {
    String key;
    Community community;
    StudentInfo studentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        community = new Community(this);
        studentInfo = new StudentInfo(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_0_main);

        key = getIntent().getStringExtra("학번");


        //공지사항 텍스트 흐르게
        TextView notice = (TextView) findViewById(R.id.notice);
        notice.setSelected(true);

        //X버튼 누르면, 로그아웃
        ImageView logout = (ImageView) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                SharedPreferences.Editor autoLogin = auto.edit();
                autoLogin.clear();
                autoLogin.commit();
                startActivity(new Intent(Stu_0_main.this, Login.class));
            }
        });

        //1: 출입관리 액티비티로 전환
        TextView stu_1_inoutcheck = (TextView) findViewById(R.id.stu_1_inoutcheck);
        stu_1_inoutcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_1_inoutcheck.class);
                startActivity(intent);
            }
        });

        //2: 외박신청 액티비티로 전환
        TextView stu_2_sleepout = (TextView) findViewById(R.id.stu_2_sleepout);
        stu_2_sleepout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_2_sleepout.class);
                startActivity(intent);
            }
        });

        //3: 커뮤니티 액티비티로 전환
        TextView stu_3_community = (TextView) findViewById(R.id.stu_3_community);
        stu_3_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_3_community.class);
                intent.putExtra("학번",key);
                intent.putExtra("Community",community);
                startActivity(intent);
            }
        });

        //4: 관생증 액티비티로 전환
        TextView stu_4_id = (TextView) findViewById(R.id.stu_4_id);
        stu_4_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_4_id.class);
                intent.putExtra("학생정보",studentInfo.searchInfo(key));
                startActivity(intent);
            }
        });


        //6: 상벌점현황 액티비티로 전환
        TextView stu_6_scorecheck = (TextView) findViewById(R.id.stu_6_scorecheck);
        stu_6_scorecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_5_scorecheck.class);
                startActivity(intent);
            }
        });

        //7: 건의/연락처 액티비티로 전환
        TextView stu_7_suggest = (TextView) findViewById(R.id.stu_7_suggest);
        stu_7_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_6_suggest.class);
                startActivity(intent);
            }
        });

        //8: 공지사항 액티비티로 전환
        LinearLayout stu_8_notice = (LinearLayout) findViewById(R.id.stu_8_notice);
        stu_8_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_7_notice.class);
                startActivity(intent);
            }
        });


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
