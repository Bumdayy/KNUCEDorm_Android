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


public class Stu_0_main extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_0_main);


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

        //1: 관생증 액티비티로 전환
        ImageView stu_1_id = (ImageView) findViewById(R.id.stu_1_id);
        stu_1_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_1_id.class);
                startActivity(intent);
            }
        });

        //2: 출입관리 액티비티로 전환
        ImageView stu_2_inoutcheck = (ImageView) findViewById(R.id.stu_2_inoutcheck);
        stu_2_inoutcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_2_inoutcheck.class);
                startActivity(intent);
            }
        });

        //3: 외박신청 액티비티로 전환
        ImageView stu_3_sleepout = (ImageView) findViewById(R.id.stu_3_sleepout);
        stu_3_sleepout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_3_sleepout.class);
                startActivity(intent);
            }
        });

        //4: 상벌점조회 액티비티로 전환
        ImageView stu_4_scorecheck = (ImageView) findViewById(R.id.stu_4_scorecheck);
        stu_4_scorecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_4_scorecheck.class);
                startActivity(intent);
            }
        });


        //5: 건의사항 액티비티로 전환
        ImageView stu_5_suggest = (ImageView) findViewById(R.id.stu_5_suggest);
        stu_5_suggest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_5_suggest.class);
                startActivity(intent);
            }
        });

        //6: 커뮤니티 액티비티로 전환
        ImageView stu_6_community = (ImageView) findViewById(R.id.stu_6_community);
        stu_6_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Stu_0_main.this, Stu_6_community.class);
                startActivity(intent);
            }
        });

        //7: 공지사항 액티비티로 전환
        LinearLayout stu_7_notice = (LinearLayout) findViewById(R.id.stu_7_notice);
        stu_7_notice.setOnClickListener(new View.OnClickListener() {
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
