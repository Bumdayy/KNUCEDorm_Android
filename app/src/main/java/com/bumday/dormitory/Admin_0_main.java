package com.bumday.dormitory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin_0_main extends FragmentActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_0_main);

        //1: 학생정보관리 버튼 누르면, 학생정보관리 액티비티로 전환
        ImageView admin_1_stuinfomng = (ImageView)findViewById(R.id.admin_1_stuinfomng);
        admin_1_stuinfomng.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_1_stuinfomng.class);
                startActivity(intent);
            }
        });

        //2: 관생증스캐너 버튼 누르면, 관생증스캔 액티비티로 전환
        ImageView admin_2_scanbarcord = (ImageView)findViewById(R.id.admin_2_scanbarcord);
        admin_2_scanbarcord.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_2_scanbarcord.class);
                startActivity(intent);
            }
        });

        //3: 상벌점부여 버튼 누르면, 상벌점부여 액티비티로 전환
        ImageView admin_3_score = (ImageView)findViewById(R.id.admin_3_score);
        admin_3_score.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_3_score.class);
                startActivity(intent);
            }
        });

        //4: 외박신청관리 버튼 누르면, 외박신청관리 액티비티로 전환
        ImageView admin_4_sleepout = (ImageView)findViewById(R.id.admin_4_sleepout);
        admin_4_sleepout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_4_sleepout.class);
                startActivity(intent);
            }
        });

        //5: 출입관리 버튼 누르면, 출입관리 액티비티로 전환
        ImageView admin_5_inout = (ImageView)findViewById(R.id.admin_5_inout);
        admin_5_inout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_5_inout.class);
                startActivity(intent);
            }
        });

        //6: 건의사항관리 버튼 누르면, 건의사항관리 액티비티로 전환
        ImageView admin_6_suggest = (ImageView)findViewById(R.id.admin_6_suggest);
        admin_6_suggest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_6_suggest.class);
                startActivity(intent);
            }
        });

        //7: 공지사항 버튼 누르면, 공지사항 액티비티로 전환
        ImageView admin_7_notice = (ImageView)findViewById(R.id.admin_7_notice);
        admin_7_notice.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this, Admin_7_notice.class);
                startActivity(intent);
            }
        });


        //X버튼 누르면, 로그아웃
        ImageView logout = (ImageView)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Admin_0_main.this,Login.class);
                startActivity(intent);
                finish();
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
            finish();
        }
    }
}
